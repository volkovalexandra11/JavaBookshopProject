package com.bookshop.Bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

@SpringBootApplication
public class BookshopApplication {

	public static void main(String[] args) {
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		ClassLoader cl2 = URLClassLoader.newInstance(new [] {"."});
//
//		URL[] urls = ((URLClassLoader)cl).getURLs();
//		Arrays.stream(urls)
//						.filter(u -> !u.getFile().contains(".jar"))
//						.forEach(u -> System.out.println(u.getPath()));

		System.out.println(System.getProperties().get("java.class.path"));

		System.out.println("HelloWorld!");
		SpringApplication.run(BookshopApplication.class, args);
	}
}
