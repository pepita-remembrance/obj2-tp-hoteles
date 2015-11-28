package ar.edu.unq.obj2.hotels.notifications.senders;

import ar.edu.unq.obj2.hotels.di.EmailSenderProvider;
import ar.edu.unq.obj2.hotels.notifications.Email;
import ar.edu.unq.obj2.hotels.notifications.EmailSender;

public interface SpyEmailSenderProvider extends EmailSenderProvider {
    default SpyEmailSender newEmailSender(){
        return new SpyEmailSender();
    }

    class SpyEmailSender implements EmailSender {
        public Email email;
        @Override public void send(Email email) {
            this.email = email;
        }
    }
}
