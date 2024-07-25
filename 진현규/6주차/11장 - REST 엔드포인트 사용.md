# 11장 - REST 엔드포인트 사용
웹 앱의 클라이언트는 백엔드를 호출할 수 있으며, 다른 백엔드 컴포넌트도 호출 할 수 있다 <br>

스프링 앱에서 REST 엔드포인트를 호출하는 3가지 방법을 알아보자 <br>
1) OpenFeign - Spring Cloud 에서 제공하는 도구이다. 새 어플리케이션에서 Rest Endpoint 를 사용할 때 이 기능을 추천한다.
2) RestTemplate - 
3) WebClient - 리액티브 프로그래밍이라는 다른 프로그래밍 방식을 사용한다.

오늘날 모든 새로운 구현에 권장되는 기능인 OpenFeign 이다. <br>

```java
@RestController
public class PaymentController {
	private Logger log = Logger.getLogger(PaymentController.class.getName());

	@PostMapping("/payment")
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment, @RequestHeader String requestId) {
		log.info("received with ID : " + requestId + " and Payment : " + payment.getAmount());

		payment.setId(UUID.randomUUID().toString());

		return ResponseEntity.
			status(HttpStatus.OK)
			.header("requestId" + requestId)
			.body(payment);
	}

}

```

위 엔드포인트를 이제 위 3가지 방법으로 호출하는 방법을 알아보겠습니다 <br>

Spring Cloud 의 OpenFeign 을 이용하면 우리는 인터페이스만 작성하면 이 도구가 구현을 해준다 <br>
- OpenFeign 으로 Rest 엔드포인트 호출을 구현하려면 OpenFeign 은 인터페이스를 구현하고 어노테이션을 사용한다

```java
name:
  service:
    url: http://localhost:10000
```

```java
@FeignClient(name="payments", url="${name.service.url")
public interface PaymentProxy {
	
	@PostMapping("/payment")
	Payment createPayment(@RequestBody Payment payment, @RequestHeader String requestId) {
		
	}
}

```

@FeignClient 어노테이션을 추가하여 이 계약에 대한 구현을 제공해야 한다고 OpenFeign 에 지시해야 한다 <br>

위 어노테이션은 요청의 기본 URI 를 지정하는 곳이다 <br>

기본 URI 는 @FeignClient 의 url 속성을 사용하여 문자열로 정의한다. <br>

* 환경마다 달라질 수 있는 URI 및 기타 세부 정보는 항상 프로퍼티 파일에 저장하고 앱에 하드 코딩해서는 안된다 <br>
프로젝트의 application.yml 에서 프로퍼티를 정의하고 ${property_name} 구문을 사용하여 소스 코드에 참조할 수 있다 <br>

인터페이스에서 선언하는 각 메소드는 Rest 엔드포인트 호출을 나타낸다 <br>
- 경로와 HTTP 메소드를 지정하려면 @GetMapping, @PostMapping, @PutMapping 등을 사용한다 <br>
- 요청 헤더를 지정하려면 @RequestHeader 를 사용한다.
- 요청 본문을 지정하려면 @RequestBody 를 사용한다.

OpenFeign 은 클라이언트 계약을 정의하는 인터페이스를 어디에서 찾을 수 있는지 알야아 한다 <br>
그래서 Config 클래스에 @EnableFeignClients 어노테이션을 추가하여 OpenFeign 기능을 활성화 하고 클라이언트 계약을 검색할 위치를 OpenFeign 에 알려준다 <br>

```java
@EnableFeignClients(basePackages = "org.example.springex.proxy")
@Configuration
public class ProjectConfig {
}

```

## Rest Template 으로 Rest 엔드포인트 호출
RestTemplate 에 문제가 있지는 않다 <br>
하지만 앱이 발전하면서 더 많은 기능이 필요해졌기 때문에 사용하는걸 추천하지 않는 것 뿐이다 <br>

RestTemplate 으로 구현하기 어려운 기능
- 엔드포인트를 동기 및 비동기식으로 모두 호출하기
- 더 적은 코드 작성 및 더 적은 예외 처리(상용구 코드 제거)
- 호출 실행 재시도 및 폴백 작업

HttpHeaders 인스턴스를 생성 및 구성하여 HTTP 헤더를 정의한다 <br>
요청 데이터(헤더와본문) 을 나타내는 httpEntity 인스턴스를 생성한다 <br>
exchange() 메소드를 사용하여 http 호출을 전송하고 http 응답을 수신한다 <br>

```java
public class PaymentProxy {
	
	private final RestTemplate rest;

	public PaymentProxy (RestTemplate rest) {
		this.rest = rest;
	}

	@Value("${name.service.url}")
	private String paymentServiceUrl;



	public Payment createPayment(Payment payment) {
		String uri = paymentServiceUrl + "/payment";

		HttpHeaders httpHeaders = new HttpHeaders();		
		httpHeaders.add("requestId", UUID.randomUUID().toString());

		HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, httpHeaders);

		ResponseEntity<Payment> responseEntity = rest.exchange(
			uri, HttpMethod.POST, httpEntity, Payment.class);
		
		return responseEntity.getBody();
		
	}
	
}
```

```java
@RestController
public class PaymentController {
	private Logger log = Logger.getLogger(PaymentController.class.getName());

	private final PaymentProxy paymentProxy;

	public PaymentController (PaymentProxy proxy) {
		this.paymentProxy = proxy;
	}


	@PostMapping("/payment")
	public Payment createPayment(@RequestBody Payment payment) {

		return paymentProxy.createPayment(payment);
	}
}
```

```java
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

```

오류가 생겨서 잘 안돼었음...

## WebClient 로 Rest 엔드포인트 호출
WebClient 는 다양한 앱에서 사용되는 도구이며 리액티브 방식이라는 방법론을 기반으로 한다 <br>
리액티브 프로그래밍은 진보된 방식이므로 따로 공부를 해야한다 <br>

리액티브 앱을 작성 중이 아니면 OpenFeign 을 사용하는걸 권장합니다 <br>

리액티브가 아닌 앱에서는 스레드가 비즈니스 흐름을 실행합니다 <br>
비즈니스 흐름은 여러 태스크로 구성되지만 이 태스크 들은 상호 독립적이지 않으며 동일 스레드가 이 Flow 를 구성하는 모든 태스크를 실행합니다 <br>

스레드는 다음 단계로 진행하기 전에 현재 단계가 완료될 때 까지 기다려야 하며, 앱이 호출을 수행할 때까지 기다릴 때마다 블로킹 된다 <br>
여기에서 두 가지 중요한 문제가 드러난다 <br>
1) I/O 호출이 스레드를 블로킹 하는 동안 스레드는 유휴 상태다. 스레드를 사용하지 않고 앱의 메모리를 계속 점유하게 된다
- 앱에 동시 요청이 10개 들어와 다른 시스템에서 상세 정보를 기다리는 동안 모든 스레드가 동시에 유휴 상태가 될 수도 있다.
2) 일부 태스크는 의존적이지 않다.

```java
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
```
```java
@Component
public class PaymentProxy {

	public PaymentProxy (WebClient webClient) {
		this.webClient = webClient;
	}

	private final WebClient webClient;

	@Value("${name.service.url}")
	private String paymentServiceUrl;

	public Mono<Payment> createPayment (String requestId, Payment payment) {
		return webClient.post()
			.uri(paymentServiceUrl + "/payment")
			.header("requestId : ", requestId)
			.body(Mono.just(payment), Payment.class)
			.retrieve()
			.bodyToMono(Payment.class);
	}

}
```

위에서 말하는 Mono 는 생산자(Producer) 를 정의한다 <br>
호출을 수행하는 메소드가 입력을 직접받지 않고 Mono 를 전송한다는 이야기 이다 <br>
이런 방식으로 독립적인 태스크를 만들어낸다 <br>

이 메소드는 값을 직접 반환하지 않는다 <br>
대신 다른 기능이 Mono 를 구독할 수 있도록 Mono 를 반환한다 <br>
이렇게 하면 앱은 스레드로 태스크를 연결하지 않고 생산자와 소비자를 통해 태스크 간 의존 관계를 연결해서 비즈니스 흐름을 구성한다 <br>

```java
	@PostMapping("/payment")
	public Mono<Payment> createPayment(@RequestBody Payment payment) {
		String requestId = UUID.randomUUID().toString();
		return paymentProxy.createPayment(requestId,payment);
	}
```

### 질의 응답
1) RestTemplate 하면 다들 잘되나요?
2) 기존에는 그럼 어떤걸로 엔드포인트를 호출을 하는거였나 
