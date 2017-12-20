package ie.gmit.sw.listener;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ie.gmit.sw.client.Request;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Thread task = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				final Queue<AsyncContext> ctxQueue = new ConcurrentLinkedQueue<AsyncContext>();
                sce.getServletContext().setAttribute("context", ctxQueue);
                
                final Queue<Request> inQueue = new ConcurrentLinkedQueue<Request>();
                final Queue<Request> outQueue = new ConcurrentLinkedQueue<Request>();

                sce.getServletContext().setAttribute("inQueue", inQueue);

                Executor inExecutor = Executors.newCachedThreadPool();
                final Executor outExecutor = Executors.newCachedThreadPool();
                final Executor userExecutor = Executors.newCachedThreadPool();
			}
		});
	}
	

}
