<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<spring:url value="/css/main.css" var="mainCss" />
<spring:url value="/css/bootstrap.min.css" var="boottrapCss" />
<spring:url value="/css/jquery.dataTables.min.css" var="tableCss" />


<spring:url value="/js/jquery.1.10.2.min.js" var="jqueryJs" />
<spring:url value="/js/jquery.dataTables.min.js" var="datatableJs" />
<spring:url value="/js/main.js" var="mainJs" />
<spring:url value="/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/js/jquery-ui.js" var="jqueryuiJs" />

<link href="${mainCss}" rel="stylesheet" />
<link href="${boottrapCss}" rel="stylesheet" />
<link href="${tableCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${jqueryuiJs}"></script>
<script src="${datatableJs}"></script>

 <script src="${bootstrapJs}"></script>
<script src="${mainJs}"></script>
<title>Auto Test</title>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #294158;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;}
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Auto Test Application</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="#">Projects</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
    </div>
    <div class="col-sm-10 text-left">
      <h1>Test case 1 <button type="button" class="btn btn-primary" id="run-testcase">Run</button></h1>
      
	  
	  <div class="alert alert-info" role="alert">
		  <textarea  >searching with gooogle!!!</textarea>
		</div>
		
		<ul id="sortable">
		  <li class="ui-state-default">
			<div class="form-group header-step-label">
				<label class="control-label">Step</label>
				<span class="ui-state-default sortable-number" >1</span>
				<span class="glyphicon glyphicon-remove"></span>
			</div>
			<div class="col-md-12 show-grid" >
			  <div class="header-step">
				
				<textarea   >enter text to search</textarea>
			  </div>
			  <div class="content-step">
				<form class="form-horizontal"> 
					<div class="form-group form-group-md multi-action-form"> 
						<label class="col-sm-3 control-label" for="formGroupActionInputSmall">Multi/step</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupActionInputSmall" placeholder="Value enter ..." type="checkbox"> 
						</div> 
					</div>  
					<div class="form-group form-group-md action-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputLarge">Action</label> 
						<div class="col-sm-9"> 
							<select class="form-control"> 
								<option value="ClickAction " title=" Equivalent to WebElement.click()">Click Action</option> 
								<option value="ClickAndHoldAction " title="Holding down the left mouse button.">Click And Hold</option> 
								<option value="ContextClickAction" title="Clicking the mouse button that (usually) brings up the contextual menu.">Context Click Action</option>
								<option value="DoubleClickAction" title="double-clicking an element.">Double Click Action</option>
								<option value="KeyDownAction" title="Holding down a modifier key.">Key Down Action </option>
								<option value="KeyUpAction" title="Releasing a modifier key.">Key Up Action </option>
								<option value="MoveMouseAction" title="Moving the mouse from its current location to another element.">Move Mouse Action  </option>
								<option value="MoveToOffsetAction" title=" Moving the mouse to an offset from an element (The offset could be negative and the element could be the same element that the mouse has just moved to).">Move To Offset Action  </option>
								<option value="SendKeysAction" selected="selected" title=" Equivalent to WebElement.sendKey(...).">Send Keys Action </option>
							</select>
						</div> 
						
					</div> 
					
					<div class="wrapper-group">
						<div class="form-group form-group-md element-type-form"> 
							<label class="col-md-3 control-label" for="formGroupInputLarge">Element Type</label> 
							
							<div class="col-sm-9"> 
								<select class="form-control"> 
									<option value="CSS" title="find element By CSS">By CSS</option> 
									<option value="XPath" title="find element By XPath">By XPath</option> 
									<option value="Name" title="find element By Name">By Name</option> 
									<option value="ID" selected="selected" title="find element By Id">By Id</option> 
								</select>
							</div> 
						</div> 
						<div class="form-group form-group-md element-define-form"> 
							<label class="col-sm-3 control-label" for="formGroupInputSmall">Element define</label> 
							<div class="col-sm-9"> 
								<input class="form-control" id="formGroupInputSmall" placeholder="Element define by ..." value="lst-ib"> 
							</div> 
						</div>
						
					</div>
					<div class="form-group form-group-md value-enter-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputSmall">Value enter</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupInputSmall" placeholder="Value enter ..." value="automation"> 
						</div> 
					</div>  
				</form>
			  </div>
			</div>
			
		  </li>
		   <li class="ui-state-default">
			<div class="form-group header-step-label">
				<label class="control-label">Step</label>
				<span class="ui-state-default sortable-number" >2</span>
				<span class="glyphicon glyphicon-remove"></span>
			</div>
			<div class="col-md-12 show-grid" >
			  <div class="header-step">
				
				<textarea  >click search</textarea>
			  </div>
			  <div class="content-step">
				<form class="form-horizontal"> 
					<div class="form-group form-group-md multi-action-form"> 
						<label class="col-sm-3 control-label" for="formGroupActionInputSmall">Multi/step</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupActionInputSmall" placeholder="Value enter ..." type="checkbox"> 
						</div> 
					</div> 
					<div class="form-group form-group-md action-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputLarge">Action</label> 
						<div class="col-sm-9"> 
							<select class="form-control"> 
								<option value="ClickAction "  selected="selected" title=" Equivalent to WebElement.click()">Click Action</option> 
								<option value="ClickAndHoldAction " title="Holding down the left mouse button.">Click And Hold</option> 
								<option value="ContextClickAction" title="Clicking the mouse button that (usually) brings up the contextual menu.">Context Click Action</option>
								<option value="DoubleClickAction" title="double-clicking an element.">Double Click Action</option>
								<option value="KeyDownAction" title="Holding down a modifier key.">Key Down Action </option>
								<option value="KeyUpAction" title="Releasing a modifier key.">Key Up Action </option>
								<option value="MoveMouseAction" title="Moving the mouse from its current location to another element.">Move Mouse Action  </option>
								<option value="MoveToOffsetAction" title=" Moving the mouse to an offset from an element (The offset could be negative and the element could be the same element that the mouse has just moved to).">Move To Offset Action  </option>
								<option value="SendKeysAction" title=" Equivalent to WebElement.sendKey(...).">Send Keys Action </option>
							</select>
						</div> 
						
					</div> 
					
					<div class="wrapper-group">
						<div class="form-group form-group-md element-type-form"> 
							<label class="col-md-3 control-label" for="formGroupInputLarge">Element Type</label> 
							
							<div class="col-sm-9"> 
								<select class="form-control"> 
									<option value="CSS"  selected="selected" title="find element By CSS">By CSS</option> 
									<option value="XPath" title="find element By XPath">By XPath</option> 
									<option value="Name" title="find element By Name">By Name</option> 
									<option value="ID" title="find element By Id">By Id</option> 
								</select>
							</div> 
						</div> 
						<div class="form-group form-group-md element-define-form"> 
							<label class="col-sm-3 control-label" for="formGroupInputSmall" >Element define</label> 
							<div class="col-sm-9"> 
								<input class="form-control" id="formGroupInputSmall" placeholder="Element define by ..." value="#sblsbb button"> 
							</div> 
					</div>
						
					</div>
					<div class="form-group form-group-md value-enter-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputSmall">Value enter</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupInputSmall" placeholder="Value enter ..."> 
						</div> 
					</div>  
				</form>
			  </div>
			</div>
			
		  </li>
		   <li class="ui-state-default">
		   <div class="form-group header-step-label">
				<label class="control-label">Step</label>
				<span class="ui-state-default sortable-number" >3</span>
				<span class="glyphicon glyphicon-remove" ></span>
			</div>
			<div class="col-md-12 show-grid" >
			  <div class="header-step">
				
				<textarea >click logo</textarea>
			  </div>
			  <div class="content-step">
				<form class="form-horizontal"> 
					<div class="form-group form-group-md multi-action-form"> 
						<label class="col-sm-3 control-label" for="formGroupActionInputSmall">Multi/step</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupActionInputSmall" placeholder="Value enter ..." type="checkbox"> 
						</div> 
					</div> 
					<div class="form-group form-group-md  action-form"> 
					
						<label class="col-sm-3 control-label" for="formGroupInputLarge">Action</label> 
						<div class="col-sm-9"> 
							<select class="form-control"> 
								<option value="ClickAction " selected="selected" title=" Equivalent to WebElement.click()">Click Action</option> 
								<option value="ClickAndHoldAction " title="Holding down the left mouse button.">Click And Hold</option> 
								<option value="ContextClickAction" title="Clicking the mouse button that (usually) brings up the contextual menu.">Context Click Action</option>
								<option value="DoubleClickAction" title="double-clicking an element.">Double Click Action</option>
								<option value="KeyDownAction" title="Holding down a modifier key.">Key Down Action </option>
								<option value="KeyUpAction" title="Releasing a modifier key.">Key Up Action </option>
								<option value="MoveMouseAction" title="Moving the mouse from its current location to another element.">Move Mouse Action  </option>
								<option value="MoveToOffsetAction" title=" Moving the mouse to an offset from an element (The offset could be negative and the element could be the same element that the mouse has just moved to).">Move To Offset Action  </option>
								<option value="SendKeysAction" title=" Equivalent to WebElement.sendKey(...).">Send Keys Action </option>
							</select>
						</div> 
						
					</div> 
					
					<div class="wrapper-group">
						<div class="form-group form-group-md element-type-form"> 
							<label class="col-md-3 control-label" for="formGroupInputLarge">Element Type</label> 
							
							<div class="col-sm-9"> 
								<select class="form-control"> 
									<option value="CSS"  selected="selected" title="find element By CSS">By CSS</option> 
									<option value="XPath" title="find element By XPath">By XPath</option> 
									<option value="Name" title="find element By Name">By Name</option> 
									<option value="ID" title="find element By Id">By Id</option> 
								</select>
							</div> 
						</div> 
						<div class="form-group form-group-md element-define-form"> 
							<label class="col-sm-3 control-label" for="formGroupInputSmall">Element define</label> 
							<div class="col-sm-9"> 
								<input class="form-control" id="formGroupInputSmall" placeholder="Element define by ..." value="#logocont a"> 
							</div> 
					</div>
						
					</div>
					<div class="form-group form-group-md value-enter-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputSmall">Value enter</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupInputSmall" placeholder="Value enter ..."> 
						</div> 
					</div>  
				</form>
			  </div>
			</div>
			
		  </li>
		   <li class="ui-state-default">
		   
		   <div class="form-group header-step-label">
				<label class="control-label">Step</label>
				<span class="ui-state-default sortable-number" >4</span>
				<span class="glyphicon glyphicon-remove"></span>
			</div>
			<div class="col-md-12 show-grid" >
			  <div class="header-step">
				
				<textarea   >Go to home page</textarea>
			  </div>
			  <div class="content-step">
				<form class="form-horizontal"> 
					<div class="form-group form-group-md multi-action-form"> 
						<label class="col-sm-3 control-label" for="formGroupActionInputSmall">Multi/step</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupActionInputSmall" placeholder="Value enter ..." type="checkbox"> 
						</div> 
					</div> 
					<div class="form-group form-group-md action-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputLarge">Action</label> 
						<div class="col-sm-9"> 
							<select class="form-control"> 
								<option value="ClickAction " title=" Equivalent to WebElement.click()">Click Action</option> 
								<option value="ClickAndHoldAction " title="Holding down the left mouse button.">Click And Hold</option> 
								<option value="ContextClickAction" title="Clicking the mouse button that (usually) brings up the contextual menu.">Context Click Action</option>
								<option value="DoubleClickAction" title="double-clicking an element.">Double Click Action</option>
								<option value="KeyDownAction" title="Holding down a modifier key.">Key Down Action </option>
								<option value="KeyUpAction" title="Releasing a modifier key.">Key Up Action </option>
								<option value="MoveMouseAction" title="Moving the mouse from its current location to another element.">Move Mouse Action  </option>
								<option value="MoveToOffsetAction" title=" Moving the mouse to an offset from an element (The offset could be negative and the element could be the same element that the mouse has just moved to).">Move To Offset Action  </option>
								<option value="SendKeysAction"  selected="selected" title=" Equivalent to WebElement.sendKey(...).">Send Keys Action </option>
							</select>
						</div> 
						
					</div> 
					
					<div class="wrapper-group">
						<div class="form-group form-group-md element-type-form"> 
							<label class="col-md-3 control-label" for="formGroupInputLarge">Element Type</label> 
							
							<div class="col-sm-9"> 
								<select class="form-control"> 
									<option value="CSS" title="find element By CSS">By CSS</option> 
									<option value="XPath" title="find element By XPath">By XPath</option> 
									<option value="Name" title="find element By Name">By Name</option> 
									<option value="ID" selected="selected" title="find element By Id">By Id</option> 
								</select>
							</div> 
						</div> 
						<div class="form-group form-group-md element-define-form"> 
							<label class="col-sm-3 control-label" for="formGroupInputSmall">Element define</label> 
							<div class="col-sm-9"> 
								<input class="form-control" id="formGroupInputSmall" placeholder="Element define by ..." value="lst-ib"> 
							</div> 
					</div>
						
					</div>
					<div class="form-group form-group-md value-enter-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputSmall">Value enter</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupInputSmall" placeholder="Value enter ..." value="thank you"> 
						</div> 
					</div>  
				</form>
			  </div>
			</div>
			
		  </li>
			<li class="ui-state-default add-button"><div class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></div></li>
		</ul>
<ul id="data-clone"><li class="ui-state-default">
			<div class="form-group header-step-label">
				<label class="control-label">Step</label>
				<span class="ui-state-default sortable-number" ></span>
				<span class="glyphicon glyphicon-remove" style="
					float: right;
					font-size: 20px;
					color: #c70505;
				"></span>
			</div>
			<div class="col-md-12 show-grid" >
			  <div class="header-step">
				
				<textarea  >...</textarea>
			  </div>
			  <div class="content-step">
				<form class="form-horizontal"> 
					<div class="form-group form-group-md multi-action-form"> 
						<label class="col-sm-3 control-label" for="formGroupActionInputSmall">Multi/step</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupActionInputSmall" placeholder="Value enter ..." type="checkbox"> 
						</div> 
					</div>  
					<div class="form-group form-group-md action-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputLarge">Action</label> 
						<div class="col-sm-9"> 
							<select class="form-control"> 
								<option value="ClickAction "  title=" Equivalent to WebElement.click()">Click Action</option> 
								<option value="ClickAndHoldAction " title="Holding down the left mouse button.">Click And Hold</option> 
								<option value="ContextClickAction" title="Clicking the mouse button that (usually) brings up the contextual menu.">Context Click Action</option>
								<option value="DoubleClickAction" title="double-clicking an element.">Double Click Action</option>
								<option value="KeyDownAction" title="Holding down a modifier key.">Key Down Action </option>
								<option value="KeyUpAction" title="Releasing a modifier key.">Key Up Action </option>
								<option value="MoveMouseAction" title="Moving the mouse from its current location to another element.">Move Mouse Action  </option>
								<option value="MoveToOffsetAction" title=" Moving the mouse to an offset from an element (The offset could be negative and the element could be the same element that the mouse has just moved to).">Move To Offset Action  </option>
								<option value="SendKeysAction" title=" Equivalent to WebElement.sendKey(...).">Send Keys Action </option>
							</select>
						</div> 
						
					</div> 
					
					<div class="wrapper-group">
						<div class="form-group form-group-md element-type-form"> 
							<label class="col-md-3 control-label" for="formGroupInputLarge">Element Type</label> 
							
							<div class="col-sm-9"> 
								<select class="form-control"> 
									<option value="CSS"   title="find element By CSS">By CSS</option> 
									<option value="XPath" title="find element By XPath">By XPath</option> 
									<option value="Name" title="find element By Name">By Name</option> 
									<option value="ID" title="find element By Id">By Id</option> 
								</select>
							</div> 
						</div> 
						<div class="form-group form-group-md element-define-form"> 
							<label class="col-sm-3 control-label" for="formGroupInputSmall" >Element define</label> 
							<div class="col-sm-9"> 
								<input class="form-control" id="formGroupInputSmall" placeholder="Element define by ..." > 
							</div> 
					</div>
						
					</div>
					<div class="form-group form-group-md value-enter-form"> 
						<label class="col-sm-3 control-label" for="formGroupInputSmall">Value enter</label> 
						<div class="col-sm-9"> 
							<input class="form-control" id="formGroupInputSmall" placeholder="Value enter ..."> 
						</div> 
					</div>  
				</form>
			  </div>
			</div>
			
		  </li></ul>
    </div>
   
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>
