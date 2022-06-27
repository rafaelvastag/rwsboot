package com.vastag.sb.services.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.PagamentoComBoleto;

@Service
public class BoletoServiceUtil {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
		Calendar c = Calendar.getInstance();
		c.setTime(instante);
		c.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(c.getTime());
	}

}
