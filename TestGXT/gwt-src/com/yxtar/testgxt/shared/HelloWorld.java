package com.yxtar.testgxt.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

public class HelloWorld {
	  interface MyUiBinder extends UiBinder<DivElement, HelloWorld> {}
	  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	  @UiField SpanElement nameSpan;

	  private DivElement root;

	  public HelloWorld() {
	    root = uiBinder.createAndBindUi(this);
	  }

	  public Element getElement() {
	    return root;
	  }

	  public void setName(String name) { nameSpan.setInnerText(name); }
	}
