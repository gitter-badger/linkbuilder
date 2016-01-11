package org.halhelper.linkbuilder.controllerproxy.controllercall;

import com.damnhandy.uri.template.UriTemplate;
import org.easymock.*;
import org.halhelper.linkbuilder.LinkBuilderImpl;
import org.halhelper.linkbuilder.argumentresolver.PageableArgumentResolver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by deinf.osvaldo on 28/12/2015.
 */
public class ControllerProxyTest {

    private LinkBuilderImpl linkBuilderImpl = new LinkBuilderImpl(null, null);

    private Method method;

    private String parameter = "parameter-value";

    private ControllerProxyTest controllerProxyTest;


    @Before
    public void setUp() throws Exception {
        method = ControllerProxyTest.class.getMethod("method", String.class);

        controllerProxyTest = ControllerProxy.createProxy(ControllerProxyTest.class, linkBuilderImpl);

    }

    @Test
    public void verifyProxySetCallToLinkBuilderImpl() {

        controllerProxyTest.method(parameter);

        // cannot use equals on method because aspects...
        assertEquals(method.toString(), linkBuilderImpl.getMethod().toString());
        assertEquals(1, linkBuilderImpl.getParameters().length);
        assertSame(parameter, linkBuilderImpl.getParameters()[0]);

    }

    public void method(String param1) {

    }


}