<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" th:href="@{/resources/stylesheets/test.css}">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Course Registration</title>
</head>

<body>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a th:href="@{/menu}"><h3>Student Registration</h3></a>
        </div>  
        <div class="col-md-6">
             <p  th:text="'User Name :' + ${session.user.username}"></p>
          	<p th:text="'Current Date :' + ${session.date}"></p> 
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='/'">
        </div>        
    </div>
</div>

</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
             <div class="dropdown-container">
          <a th:href="@{/showcourse}">Course Registration </a>
          <a th:href="@{/showstudentform}">Student Registration </a>
          <a th:href="@{/showAll}">Student Search </a>
        </div>
        <a th:href="@{/userall}">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
        <form th:action="@{/updateStudent}" method="Post" th:object="${bean}">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Details</h2>
            <div class="row mb-4">
                        <h3 style="color:red;" align="center"></h3>
            
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                    <input type="text" readonly="readonly"  th:field="*{studentid}" class="form-control" id="studentID"/>
                </div>
                <div class="col-md-4">
                <label

				th:if="${#fields.hasErrors('studentid')}"
				th:errors="*{studentid}" style="color:red;">Error</label>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{studentname}" class="form-control" id="name"/>
      
                </div>
                <div class="col-md-4">
                <label

				th:if="${#fields.hasErrors('studentname')}"
				th:errors="*{studentname}" style="color:red;">Error</label>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" th:field="*{date}" class="form-control" id="dob"/>
                
                </div>
                <div class="col-md-4">
                <label

				th:if="${#fields.hasErrors('date')}"
				th:errors="*{date}" style="color:red;">Error</label>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <input type="radio" class="form-check-input" th:field="*{gender}" id="gridRadios1" value="Male"/>
                        <label class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input type="radio" class="form-check-input" th:field="*{gender}" id="gridRadios2" value="Female"/>
                        <label class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                        
                    </div>
    
                </div>
                <div class="col-md-4">
                <label

				th:if="${#fields.hasErrors('gender')}"
				th:errors="*{gender}" style="color:red;">Error</label>
                </div>
            </fieldset>
    
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{phone}" class="form-control" id="phone" placeholder="+95 "/>
                
                </div>
                <div class="col-md-4">
                <label

				th:if="${#fields.hasErrors('phone')}"
				th:errors="*{phone}" style="color:red;">Error</label>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" aria-label="Education" id="education" th:field="*{education}">
                        <option value="Bachelor of Information Technology">Bachelor of Information Technology</option>
                        <option value="Diploma in IT">Diploma in IT</option>
                        <option value="Bachelor of Computer Science">Bachelor of Computer Science</option>
    
                    </select>
                    <div class="col-md-4">
                    
                </div>
                </div>
            </div>
            <fieldset class="row mb-4">
            
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
   				
   				<div class="col-md-6">
   				<div th:if="${#fields.hasErrors('course')}"
				th:errors="*{course}" style="color:red;">Error</div>
                    <div class="form-check-inline col-md-3 mb-3" th:each="course: ${courses}">
                        <input class="form-check-input" type="checkbox" th:field="*{course}" id="gridRadios1" th:value="${course.id}">
                       
                        <label class="form-check-label" for="gridRadios1" th:text="${course.course_name}">
                           
                        </label>
                        
                    </div>
        
                    </div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-4">
                </div>
    
                <div class="col-md-4">
             
                        <button type="submit" class="btn btn-secondary">
                          
                            <span>Update</span>
                        </button>
                          <a th:href="@{/deletestudent(id=${bean.studentid})}" class="btn btn-danger">               
    				Delete</a>
                    <!-- Button trigger modal -
                   
    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Ok</button>
                                    <button type="button" class="btn btn-danger">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
    
    
    
    
            </form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>

</html>

