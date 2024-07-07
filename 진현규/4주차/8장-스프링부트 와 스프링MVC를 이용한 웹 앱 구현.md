# 8장 - 스프링 부트와 스프링 MVC 를 이용한 웹 앱 구현

## 동적 뷰를 사용한 웹 앱 구현
동적으로 데이터를 바꾸기 위해서는 디스패처 서블릿이 HTTP 응답으로 렌더링 하는데 필요한 뷰 이름을 HTTP 응답으로 반환 이 부분을 수정해야 한다. <br>

디스패처 서블릿이 뷰 리졸버에게 뷰 담당 부분을 위임할 때 컨트롤러에서 뷰로 데이터를 전송하는 것까지 표시한다 <br>
```java
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

우리는 동적인 웹을 위해서 스프링과 호환이 잘되는 템플릿 엔진인 타밈리프를 사용한다 <br>

### HTTP 요청에서 데이터 얻기
@RequestParam 은 메소드의 매개변수 이름과 동일한 이름의 HTTP 요청 매개변수 값을 가져와야 한다고 스프링에 알려준다. <br>
```java
	@GetMapping("/home1")
	public String home(@RequestParam String color, Model model) {
		model.addAttribute("color",color);
		model.addAttribute("userName","kyu");
		return "index.html";
	}
```

클라이언트 요청 -> 톰캣 -> 디스패처 서블릿(스프링 웹) -> 핸들러 매핑(컨트롤러 찾아줌) -> 뷰 리졸버(뷰 찾아줌) -> 디스패처 서블릿 -> 클라이언트 <br>

컨트롤러 액션에 새 매개변수를 추가해서 수신할 수 있다 <br>
```java
@Controller
public class MainController {

	@RequestMapping("/home2")
	public String home2 (
		@RequestParam(required=false) String name,
		@RequestParam(required=false) String color,
		Model model) {
		model.addAttribute("name", name);
		model.addAttribute("color", color);
		return "home.html";
	}
}
```

요청 매개변수는 기본적으로 필수다, 클라이언트가 값을 제공하지 않으면 서버는 HTTP '404 Bad Request' 상태의 응답을 반환한다 <br>
비필수 값으로 설정하려면 required=false 속성을 사용해야 한다 <br>

### 경로 변수로 클라이언트에서 서버로 전달
경로 변수를 사용하는 것도 클라이언트에서 서버로 데이ㅓ를 전송하는 방법 중 하나이다. <br>
하지만 HTTP 요청 매개변수를 사용하는 대신 다음 제시된 것처럼 경로에 변수 값을 직접 설정한다.<br>
```java
https://localhost:8080/home/blue
```

더 이상 키로 값을 식별하지 않고, 경로의 정확한 위치에서 해당 값을 가져오기만 하면 된다 <br>
설정한 경로의 정확한 위치에서 해당 값을 가져오기만 하면 된다 <br>
서버 측에서는 특정 위치의 경로에서 해당 값을 추출한다 <br>

경로 변수로 값 두 개를 제공할 수 있지만, 일반적으로 복수 값은 사용하지 않는 편이 좋다 <br>
복수 값이 필요하다면 경로 변수 대신 요청 매개변수(쿼리 파라미터)를 사용하는 편이 좋다 <br>

요청 매개변수 <br>
- 비필수 값과 함께 사용할 수 있다. <br>
경로 변수 <br>
- 비필수 값을 사용하면 안 된다.
- 언제든 경로 변수를 세 개 이상 전소앟면 안 된다. 최대 2개까지가 좋다 ex) /api/v1/login 여기 까지
- 쿼리 표현식 보다 읽기 쉽다.

```java
	@RequestMapping("/home3/{id}")
	public String home3(@PathVariable("id") String id, Model model) {
		model.addAttribute("id", id);
		return "index.html";
	}
```

## HTTP GET & POST 매소드 사용
경로(path) 와 동사(verb) 는 HTTP 요청을 식별하는데 사용된다<br>
http 메소드의 목적은 클라이언트가 요청하는 동작을 정의하는 것이다 <br>
get 을 사용하면 데이터를 조회하는 액션만 정의한다, 클라이언트가 서버에서 무언가를 얻고 싶을 때 할 수 있는 방법이며 get 호출로 db 가 변경되지 않는다 <br>
get 메소드를 사용해서도 db 데이터를 변경할 수 있다, 하지만 목적에 맞지 않으므로 사용해서는 안된다<br>

지금까지는 컨트롤러의 특정 액션에 도달하고자 요청 경로에 의존헀지만, 좀 더 복잡한 시나리오에서는 서로 다른 HTTP 메소드를 사용한다면 <br>
한 컨트롤러의 여러 액션에 동일한 경로를 할당할 수 있다 <br>

http 메소드는 동사로 정의되며 클라이언트 의도를 나타낸다 <br>
- GET : 데이터 조회
- POST : Create
- PUT : Update
- DELETE : Delete
- PATCH : Update 긴 한데 컬럼을 부분적 변경
 
put,patch 를 명확하게 구분하는 것은 좋지만, 항상 그럴수는 없다 <br>

예시를 한번 보자<br>
1) 목록에 있는 모든 제픔 보기
2) 목록에 제품 추가

```java
public class Product {
	private String name;
	private double price;
	
	//getter, setter
}

@Service
public class ProductService {

	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product) {
		products.add(product);
	}

	public List<Product> findAllProduct() {
		return products;
	}
}
```

컨트롤러는 서비스에 구현한 사용 사례를 호출한다 <br>
컨트롤러는 클라이언트에서 새 제품에 대한 데이터를 가져오고 서비스를 호출하여 목록에 추가하며, 제품목록을 가져와 뷰로 보낸다 <br>

```java
@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController (ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/api/products")
	public String viewProducts (Model model) {

		Product product = new Product();
		product.setName("진현규");
		product.setPrice(1000000000.10);
		productService.addProduct(product);

		List<Product> allProduct = productService.findAllProduct();
		model.addAttribute("products", allProduct);
		return "products.html";
	}

}
```

1) 클라이언트가 /api/products 경로에 대한 http 요청을 전송한다.
2) 디스패처 서블릿은 핸들러 매핑을 사용하여 /api/products 경로를 호출하는 컨트롤러 액션을 찾는다.
3) 디스패처 서블릿이 컨트롤러의 액션을 호출한다.
4) 컨트롤러가 서비스에 제품목록을 요청하고 렌더링 하도록 제품 목록을 뷰에 전달한다.
5) 뷰가 렌더링되어 http 응답에 포함된다.
6) http 응답이 클라이언트로 재전송된다.


만약에 메소드 에 @GetMapping, @RequestMapping 을 선언해두지 않으면 기본적으로 http get 을 사용한다 <br>

```java
@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController (ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/api/products")
	public String viewProducts(Model model) {

		List<Product> allProduct = productService.findAllProduct();
		model.addAttribute("products", allProduct);
		return "products.html";
	}

	@PostMapping("/api/add")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setName("진현규");
		product.setPrice(1000000000.10);
		productService.addProduct(product);

		return "redirect:/api/products";
	}
}
```

위 코드를 사용하면 새로운 제품을 추가하고 바로 조회가 가능하다 <br>

지금은 파라미터에 @RequestParam 을 사용하여 각각의 필드를 받았지만 <br>
보통은 객체를 주입을 많이 받는다 <br>
```java
	@GetMapping("/api/v1/products")
	public String addProducst1(Product product, Model model) {
		productService.addProduct(product);
		model.addAttribute("product", product);
		return "products.html";
	}
```

- 오늘날 웹 앱은 동적 페이지(동적 뷰) 를 지원하며, 동적 페이지는 요청에 따라 다른 콘텐츠를 표시한다.
  - 현재 99% 이상의 웹사이트는 동적 페이지 일것 이라고 생각한다 
- 동적 뷰는 표시할 정보를 알려고 컨트롤러에서 변수 데이터를 가져온다
- 스프링 앱에서 동적 페이지를 구현하는 쉬운 방법은 타임리프 템플릿 엔진을 사용하는 것이다. ex) JSP , 프리마커 도 가능
- 















