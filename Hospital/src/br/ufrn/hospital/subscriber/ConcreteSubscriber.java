package br.ufrn.hospital.subscriber;

import br.ufrn.hospital.controller.HospitalController;
import br.ufrn.hospital.controller.WaitNewTopics;

public class ConcreteSubscriber extends AbstractSubscriber {

	private HospitalController hospitalController = null;
	private WaitNewTopics waitNewTopics = null;

	public ConcreteSubscriber(String topic,
			HospitalController hospitalController) {
		super(topic, "http://localhost:8080/hub/hub/", "127.0.0.1");
		this.hospitalController = hospitalController;
	}

	public ConcreteSubscriber(String topic, WaitNewTopics waitNewTopics) {
		super(topic, "http://localhost:8080/hub/hub/", "127.0.0.1");
		this.waitNewTopics = waitNewTopics;
	}

	@Override
	public void TreatMessage(String message) {

		if (hospitalController != null) {
			hospitalController.tratarNotificacaoPaciente(
					this.subscribe.getTopic(), message);
		} else if (waitNewTopics != null) {
			waitNewTopics.novoTopicoCadastrado(message.trim());
		}
	}

}
