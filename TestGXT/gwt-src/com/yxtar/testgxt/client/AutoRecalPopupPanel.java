package com.yxtar.testgxt.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * AutoRecalPopupPanel is a panel which extend PopupPanel and will handle browser resizing and scrolling problem automatically
 * 
 * @author Jimmy Lo
 */
public class AutoRecalPopupPanel extends PopupPanel {

	/** scroll enable flag */
	private boolean scrollFlg = false;
	/** panel left */
	private int left = 0;
	/** panel top*/
	private int top = 0;

	/**
	 * Constructor of AutoRecalPopupPanel
	 * fix position with browser
	 */

	public AutoRecalPopupPanel(){
		this.enableFixPosWithBroswer();
	}

	/**
	 * Constructor of AutoRecalPopupPanel
	 * @param scrollFlg	true to enable scroll function, false to enable fixed position with browser otherwise
	 * @param animatedFlg	true to enable animation
	 */
	public AutoRecalPopupPanel(boolean scrollFlg, boolean animatedFlg){
		this.scrollFlg = scrollFlg;

		if(!scrollFlg){
			this.enableFixPosWithBroswer();
		}else{
			this.enableScrollWithBroswer();
		}
		this.setAnimationEnabled(animatedFlg);
	}

	public boolean getScrollFlg(){
		return this.scrollFlg;
	}
	public void setLeft(int left){
		this.left = left;

	}
	public int getLeft(){
		return this.left; 
	}

	public void setTop(int top){
		this.top = top;

	}
	public int getTop(){
		return this.top;
	}

	/**
	 * recalculation panel position
	 */
	public void recalPopupPanelPosition(){
		int left = (Window.getClientWidth() - this.getOffsetWidth()) / 2;
		int top = (Window.getClientHeight() - this.getOffsetHeight()) / 2;
		if(left < 0 ){
			left = 0;
		}
		if(top < 0){
			top = 0;
		}

		if(getScrollFlg()){
			setLeft(left);
			setTop(top);
		}

		top = top + Window.getScrollTop();
		left = left + Window.getScrollLeft();		
		this.setPopupPosition(left, top);
	}

	/**
	 * calculated the panel position and show the panel
	 */
	public void calPositionAndShow(){

		this.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = (Window.getClientHeight() - offsetHeight ) / 2;

				if(left < 0 ){
					left = 0;
				}
				if(top < 0){
					top = 0;
				}

				if(getScrollFlg()){
					setLeft(left);				
					setTop(top);	

				}
				top = top + Window.getScrollTop();	
				left = left + Window.getScrollLeft();	
				setPopupPosition(left, top);
			}
		});

	}
	
	/**
	 * calculated the panel position and show the panel with time delay
	 *  @param delayMillis	delay time
	 */
	public void calPostionAndShowDelay(int delayMillis){
		Timer resizeTimer = new Timer() {  
			@Override
			public void run() {
				calPositionAndShow();

			}
		};


		resizeTimer.cancel();
		resizeTimer.schedule(delayMillis);

	}

	/**
	 * enable fixed popup panel position with browser
	 */
	private void enableFixPosWithBroswer(){
		//recalculate popup panel position
		Window.addResizeHandler(new ResizeHandler() {

			Timer resizeTimer = new Timer() {  
				@Override
				public void run() {
					recalPopupPanelPosition();
				}
			};

			@Override
			public void onResize(ResizeEvent event) {
				resizeTimer.cancel();
				resizeTimer.schedule(250/2);
			}
		});
	}

	/**
	 * enable to change position while scrolling in the browser
	 */
	protected void enableScrollWithBroswer(){
		//recalculate popup panel position

		Window.addResizeHandler(new ResizeHandler() {

			Timer resizeTimer = new Timer() {  
				@Override
				public void run() {
					recalPopupPanelPosition();
				}
			};

			@Override
			public void onResize(ResizeEvent event) {
				resizeTimer.cancel();
				resizeTimer.schedule(250/2);
			}
		});

		Window.addWindowScrollHandler(new ScrollHandler(){

			Timer resizeTimer = new Timer() {  
				@Override
				public void run() {
					int left = (Window.getClientWidth() - getOffsetWidth()) / 2;
					int top = (Window.getClientHeight() - getOffsetHeight()) / 2;

					if(left < 0 && Window.getScrollLeft() < getOffsetWidth()){
						left = getLeft();

					}else{
						left = getLeft()+Window.getScrollLeft();
					}
					if(top < 0 && Window.getScrollTop() < getOffsetHeight()){
						top = getTop();
					}else{
						top = getTop()+Window.getScrollTop();
					}
					setPopupPosition(left, top);
				}
			};
			@Override
			public void onWindowScroll(ScrollEvent arg0) {
				resizeTimer.cancel();
				resizeTimer.schedule(20);
			}

		});
	}
}
