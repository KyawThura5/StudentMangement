<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<link rel="stylesheet" th:href="@{/resources/stylesheets/test.css}">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<title></title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
          </div>
          <p style="color:red;" th:text="${error}"></p>
        </div>
        <form class="login-form" th:action="@{/login}" name="confirm" method="GET" th:object="${loginBean}">              
           <input required="required" type="text" class="form-control" id="email" th:field="*{id}" placeholder="ID"/>
          
          <input required="required" type="password" class="form-control" id="email" th:field="*{password}" placeholder="PASSWORD"/>
          
          <button>login</button>
          <p class="message">Not registered? <a th:href="@{/createuser}">Create an account</a></p>
        </form>
      </div>
    </div>
</body>

</html>