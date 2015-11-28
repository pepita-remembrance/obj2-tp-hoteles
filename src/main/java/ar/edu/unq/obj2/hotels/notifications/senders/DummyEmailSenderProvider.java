package ar.edu.unq.obj2.hotels.notifications.senders;

import ar.edu.unq.obj2.hotels.di.EmailSenderProvider;
import ar.edu.unq.obj2.hotels.notifications.EmailSender;

public interface DummyEmailSenderProvider extends EmailSenderProvider {
    default EmailSender newEmailSender(){ return (email)->{}; }
}
