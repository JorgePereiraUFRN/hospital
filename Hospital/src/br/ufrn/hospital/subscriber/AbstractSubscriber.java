/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.hospital.subscriber;

import br.ufrn.hospital.exceptions.ComunicationException;
import br.ufrn.hospital.exceptions.TopicDoesNotExistException;
import br.ufrn.model.SubscribeBean;
import br.ufrn.model.Topics;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.util.Random;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;


public abstract class AbstractSubscriber implements TreatMessage {

    protected SubscribeBean subscribe = new SubscribeBean();
    private Client client = Client.create();
    private WaitUpdates waitUpdates;
    private String uriHub;
/*
    classe responsavel por se realizar a subcri��o no hub e cancelar a subsbri��o
    recebe como parametro no contrutor o identificador do topico no qual deseja se subscrever
    a url do hub e o endere�o ip da m�quina no qual o subscriber est� sendo executado
    */
    public AbstractSubscriber(String topic, String uriHub, String ipAddress) {
        subscribe.setTopic(topic);
        subscribe.setPort(new Random().nextInt(60000) + 1025);
        subscribe.setAddress(ipAddress);
        this.uriHub = uriHub;
    }

    /*m�todo respos�vel por solicitar a subscri��o em um t�pico ao hub, o identificador do t�pico no qual
    ser� subscrito � aquele que recebeu por parametro no construtor mda classe. Este m�todo pode levatar
    duas exce��es TopicDoesNotExistException, caso o t�pico no qual se deseja subscrever n�o exista e
    ComunicationException, caso ocorram problemas na comunica��o entre o subscribe e o hub */
    
    public void subscribe() throws ComunicationException, TopicDoesNotExistException {
        String response;
        try {
            WebResource resource = client.resource(uriHub + "subscribe");
            response = resource.type("application/json").put(String.class, subscribe);
        } catch (Exception e) {
            throw new ComunicationException(e.getMessage());
        }
        if (response.equalsIgnoreCase("success")) {
            WaitUpdates();
        } else {
            throw new TopicDoesNotExistException(response);
        }

    }
   /* 
    m�todo respons�vel por solicitar ao hub o cancelamento de sua subscri��o. Este m�tod pode levantar
    a exce��o ComunicationException, caso ocorram problemas na comunica��o entre o subscribe e o hub
*/
    public void unsubscribe() throws ComunicationException {
        try {
            WebResource resource = client.resource(uriHub + "unsubscribe/?address=" + subscribe.getAddress()
                    + "&idTopic=" + subscribe.getTopic() + "&port=" + String.valueOf(subscribe.getPort()));
            resource.delete();
        } catch (Exception e) {
            throw new ComunicationException(e.getMessage());
        }
        waitUpdates.finalize();
    }

    /*m�todo que cria um objeto do tipo WaitUpdates, este objeto aguardar� pelas notifica��es e as enviar�
    para o subscriber no momento em que receb�-las
    */
    private void WaitUpdates() {
        waitUpdates = new WaitUpdates(subscribe.getPort(), this);
        waitUpdates.start();
    }
    
    public String[] getAllTopics(){
    	WebResource resource = client.resource(uriHub + "allTopics");
    	Topics topics = resource.type("application/json").get(Topics.class);
    	
    	return topics.getTopics();
    }
}
