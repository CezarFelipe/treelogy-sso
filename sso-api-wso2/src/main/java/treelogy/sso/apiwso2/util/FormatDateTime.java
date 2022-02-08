package treelogy.sso.apiwso2.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FormatDateTime {

	public String nowDateTime() {

		// data/hora atual
		LocalDateTime agora = LocalDateTime.now();

		// formatar a data
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String dataFormatada = formatterData.format(agora);

		// formatar a hora
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaFormatada = formatterHora.format(agora);

		String datetime = dataFormatada + " " + horaFormatada;

		return datetime;

	}

	public Timestamp timeStamp(String datetime) {
		
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/uuuu HH:mm:ss");
			Date parsedDate = dateFormat.parse(datetime);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			
			return timestamp;

		} catch (Exception e) { // this generic but you can control another types of exception
			// look the origin of excption
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
