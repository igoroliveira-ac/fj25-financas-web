package br.com.caelum.financas.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Stateless
public class Agendador {
	
	@Resource
	private TimerService timerService;

	private static int totalCriado;

	public void agenda(String expressaoMinutos, String expressaoSegundos){
		ScheduleExpression expression = new ScheduleExpression().hour("*").minute(expressaoMinutos).second(expressaoSegundos);
		TimerConfig config = new TimerConfig();
		config.setInfo(expression.toString());
		//config.setPersistent(false);
		this.timerService.createCalendarTimer(expression, config);
		System.out.println("Agendamento: " + expression);
	}
	
	@PostConstruct
	public void posConstruct(){
		System.out.println("criado agendador");
		totalCriado++;
	}
	
	@PreDestroy
	public void preDestuicao(){
		System.out.println("destruindo agendador");
		//totalCriado--;
	}

	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}
	
	@Timeout
	public void verificacaoPeriodo(Timer timer){
		System.out.println(timer.getInfo());
	}

}
