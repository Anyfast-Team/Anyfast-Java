package anyfast.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EasyHTTP {
	private String method, url, host, uri;
	private int port;
	private List<Header> headers = new ArrayList<Header>();
	private List<Header> retheaders = new ArrayList<Header>();
	private Socket s;
	private int return_code = 0;
	private class Header{
		public String name,value;
	}
	public EasyHTTP(String method,String url) throws MalformedURLException {
		this.method = method;
		this.url = url;
		int i=0;
		char last = 0;
		URL u = new URL(url);
		if(!u.getProtocol().equals("http")){
			throw new MalformedURLException();
		}
		host = u.getHost();
		uri = u.getPath()+"?"+u.getQuery();
	}
	public void setHeader(String name,String value){
		for(Header h : headers){
			if(h.name.equals(name)){
				h.value = value;
				return;
			}
		}
		Header h = new Header();
		h.name = name;
		h.value = value;
		headers.add(h);
	}
	public void clearHeader(){
		headers = new ArrayList<Header>();
	}
	public EasyHTTP send() throws UnknownHostException, IOException{
		String header = "";
		retheaders = new ArrayList<Header>();
		header += method + " " + uri + " HTTP/1.1\n";
		header += "Host: " + host;
		for(Header h : headers){
			header += h.name+": "+h.value+"\n";
		}
		header += "\n";
		s = new Socket(host,port);
		s.getOutputStream().write(header.getBytes());
		s.getOutputStream().flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String line = br.readLine();
		String code = "";
		String status = "";
		int i=0;
		for(char now : line.toCharArray()){
			if(now==' '){
				i++;
				continue;
			}
			if(i==1) {
				code += now;
			} else {
				status += now;
			}
		}
		return_code = Integer.parseInt(code);
		for(;;){
			line = br.readLine();
			if(line.equals("") || line.equals("\n"))
				break;
			Header h = new Header();
			i=0;
			for(char n : line.toCharArray()){
				if(n==':' || n==' '){
					i++;
					if(i>=3){
						h.value += "";
					}
					continue;
				}
				if(i==0){
					h.name += n;
				} else if(i>=3) {
					h.value += n;
				}
			}
			retheaders.add(h);
		}
		return this;
	}
	public OutputStream getOutputStream() throws IOException{
		return s.getOutputStream();
	}
	public InputStream getInputStream() throws IOException{
		return s.getInputStream();
	}
	public void close() throws IOException{
		s.close();
	}
	public int getCode(){
		return return_code;
	}
	public String getReturnHeader(String name){
		for(Header h : headers){
			if(h.name.equals(name)){
				return h.value;
			}
		}
		return null;
	}
	public String[] getAllReturnHeader(){
		String[] ret = new String[headers.size()];
		int point = 0;
		for(Header h : headers){
			ret[point] = h.name;
			point++;
		}
		return ret;
	}
}
