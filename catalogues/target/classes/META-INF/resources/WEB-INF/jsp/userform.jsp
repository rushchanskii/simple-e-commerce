<%@ include file="common/header.jspf"%>

<div class="container-fluid d-flex p-5  allign-middle justify-content-center">
<div class="col-lg-4 justify-content-center align-middle">
<form:form method="post" action="adduser" modelAttribute="user"> 

<!-- Text input -->
  <div class="form-outline mb-0">
    <form:input type="text" path="loginName" id="form6Example4" class="form-control" />
    <form:label class="form-label" path="loginName" for="form6Example4">login</form:label>
  </div>
  <div class="form-outline mb-0">
    <form:input type="text" path="encriptedPassword" id="form6Example4" class="form-control" />
    <form:label class="form-label" path="encriptedPassword" for="form6Example4">password</form:label>
  </div>

  <!-- 2 column grid layout with text inputs for the first and last names -->
  <div class="row mb-1">
    <div class="col">
      <div class="form-outline">
        <form:input type="text" path="firstName" id="form6Example1" class="form-control" />
        <form:label class="form-label" path="firstName" for="form6Example1">First name</form:label>
      </div>
    </div>
    <div class="col">
      <div class="form-outline">
        <form:input type="text" path="lastName" id="form6Example2" class="form-control" />
        <form:label class="form-label" path="lastName" for="form6Example2">Last name</form:label>
      </div>
    </div>
  </div>

  

  <!-- Text input -->
  <div class="form-outline mb-1">
    <form:input type="text" path="address" id="form6Example4" class="form-control" />
    <form:label class="form-label" path="address" for="form6Example4">Address</form:label>
  </div>

  <!-- Email input -->
  <div class="form-outline mb-1">
    <form:input type="email" path="eMail" id="form6Example5" class="form-control" />
    <form:label class="form-label" path="eMail" for="form6Example5">Email</form:label>
  </div>

  <!-- Number input -->
  <div class="form-outline mb-1">
    <form:input type="number" path="phoneNumber" id="form6Example6" class="form-control" />
    <form:label class="form-label" path="phoneNumber" for="form6Example6">Phone</form:label>
  </div>

  
 

  <!-- Submit button -->
  <button type="submit" class="btn btn-primary btn-block mb-4">Create user</button>
</form:form>
</div>
</div>

<%@ include file="common/footer.jspf"%>