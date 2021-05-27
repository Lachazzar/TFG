package es.udc.tfgproject.backend.model.entities;

public class Alert {

    private String title;
    private String message;
    private boolean aRecetar;

    public Alert(String title, String message, boolean aRecetar) {
	this.title = title;
	this.message = message;
	this.aRecetar = aRecetar;
    }

    public final String getTitle() {
	return title;
    }

    public final void setTitle(String title) {
	this.title = title;
    }

    public final String getMessage() {
	return message;
    }

    public final void setMessage(String message) {
	this.message = message;
    }

    public final boolean isaRecetar() {
	return aRecetar;
    }

    public final void setaRecetar(boolean aRecetar) {
	this.aRecetar = aRecetar;
    }

}
