package MessageObserver;

import Model.Client;

public interface Sender {
     boolean SendMessage(Client c);
     String MessageReceivedVerification(Client c, Message msg);
    }
