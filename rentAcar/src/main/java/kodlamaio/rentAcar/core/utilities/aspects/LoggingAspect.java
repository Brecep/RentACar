package kodlamaio.rentAcar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.json.simple.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {

	@Around("execution(* deleteById(int))")
	public void beforeLog(ProceedingJoinPoint proceedingJoinPoint) {
		try {
			System.out.println("before logging");
			proceedingJoinPoint.proceed();
			System.out.println("after returnig");
		} catch (Throwable e) {
			System.out.println("after throwing");
			e.printStackTrace();
		}
		System.out.println("after finally");
	}
	
	
	JSONArray jsonArray=new JSONArray();
	@Before("execution(* import kodlamaio.rentAcar.entities.conretes*.*)")
	public void jsonWriter(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		ObjectMapper mapper = new ObjectMapper();
		Object[] parameters = joinPoint.getArgs();
		File file = new File("C://Logs/opearations.json");

		try (FileWriter fileWriter = new FileWriter(file, true)) {
			JsonObject jsonObject = Json.createObjectBuilder().add("date : ", LocalDate.now().toString() + '\n') // String
					.add("className : ", joinPoint.getTarget().getClass().getSimpleName() + "\n")
					.add("methodName : ", signature.getMethod().getName() + '\n')
					.add("parameters : ", mapper.writeValueAsString(parameters) + "\n").build();

			jsonArray.add(jsonObject);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			bWriter.write(jsonArray.toString());
			bWriter.newLine();
			bWriter.close();

			System.out.println(jsonArray);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * @Before("execution(* deleteById(int))") // bütün deletebyId'lerde çalışıyor
	 * paket ismini vermemiz gerek public void beforeLog(JoinPoint joinpoint) {
	 * 
	 * MethodSignature signature=(MethodSignature) joinpoint.getSignature();
	 * System.out.println("before brand manager deletById");
	 * System.out.println(joinpoint.getArgs()[0]);
	 * System.out.println(joinpoint.FIELD_GET);
	 * System.out.println(joinpoint.getSignature()); }
	 */

	/*
	 * @After("execution(* deleteById(int))") // bütün deletebyId'lerde çalışıyor
	 * public void afterLog() {
	 * System.out.println("before brand manager deletById"); }
	 * 
	 * @Pointcut public void pointCut() { System.out.println("dummy method"); }
	 */
}
