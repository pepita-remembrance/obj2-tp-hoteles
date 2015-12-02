package ar.edu.unq.obj2.hotels.notifications;

public interface Notifier<T> {
    public void sendNotification(T someObject);
}
