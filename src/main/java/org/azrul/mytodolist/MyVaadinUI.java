package org.azrul.mytodolist;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.azrul.langkuik.Langkuik;
import org.azrul.langkuik.framework.relationship.DefaultRelationManagerFactory;
import org.azrul.langkuik.framework.relationship.RelationManagerFactory;


@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "org.azrul.mytodolist.AppWidgetSet")
    public static class Servlet extends VaadinServlet   {
    }

    @Override
    protected void init(VaadinRequest request) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.azrul.mytodolist_MyTodoList_war_1.0-SNAPSHOT_PU");
        RelationManagerFactory rmf = new DefaultRelationManagerFactory();
        Langkuik langkuik = new Langkuik();
        //langkuik.massIndex(emf);
        langkuik.initLangkuik(emf, (UI)this, rmf);
    }

}
