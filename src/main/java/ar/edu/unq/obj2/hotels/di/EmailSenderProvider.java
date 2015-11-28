package ar.edu.unq.obj2.hotels.di;

import ar.edu.unq.obj2.hotels.notifications.EmailSender;

public interface EmailSenderProvider {
    public EmailSender newEmailSender();
}