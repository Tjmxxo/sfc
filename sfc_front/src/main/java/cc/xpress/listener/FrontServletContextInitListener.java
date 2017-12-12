package cc.xpress.listener;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-30 11:16
 * @modified By:
 */
public class FrontServletContextInitListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }
}
