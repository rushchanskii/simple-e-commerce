<%@ include file="common/header.jspf"%>
<div class="container-fluid d-flex p-5 justify-content-center allign-middle">
<div class="row justify-content-center align-middle">
   

<div class="row form mb-5">
      <form name="f" action="/login" method="post">               
            <fieldset>
              <div class="mb-5">
                <legend>Please Login</legend>
              </div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>        
                <br>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>    
                <div class="form-actions">
                    <button type="submit" class="btn btn-success">Log in</button>
                </div>
            </fieldset>
        </form>
        
<a href="/" class="btn btn-warning">Continue as Guest</a> 
</div>
</div>
</div>
<%@ include file="common/footer.jspf"%>