package ar.edu.unq.obj2.hotels.notifications.senders;

import ar.edu.unq.obj2.hotels.di.EmailSenderProvider;
import ar.edu.unq.obj2.hotels.notifications.EmailSender;

interface ConsoleEmailSenderProvider extends EmailSenderProvider {
    default EmailSender newEmailSender(){
        return (email)-> System.out.println(email.readable());
    }
}
