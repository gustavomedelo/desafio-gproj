<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fieldset class="form-group">
	<div class="row">
		<div class="col-sm-6">
			<label><b>Nome <span class="field-required">*</span></b></label>
			<input type="text" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="name" value="${project.name}" required /><br />
	     </div>
	    <div class="col-sm-6">
			<label><b>Gerente <span class="field-required">*</span></b></label>
			<form:select class="form-control" disabled="${project.readOnly}" path="project.manager" id="comboGerente" name="comboGerente" required="required">
				<form:option value="" label="Selecione" />
		        <form:options items="${project.selectManager}" itemValue="key" itemLabel="label"/>
			</form:select><br />
	    </div>
	</div>
</fieldset>

<fieldset class="form-group">
	<div class="row">
		<div class="col-sm-12">
			<label><b>Descrição</b></label>
			<input type="text" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="description" value="${project.description}" /><br />
	    </div>
	</div>
</fieldset>

<fieldset class="form-group">
	<div class="row">
		<div class="col-sm-4">
			<label><b>Data início</b></label>
			<input type="date" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="initDate" value="${project.initDate}" /><br />
	    </div>
	    <div class="col-sm-4">
			<label><b>Data previsão fim</b></label>
			<input type="date" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="endDateForecast" value="${project.endDateForecast}"/><br />
	    </div>
	    <div class="col-sm-4">
			<label><b>Data fim</b></label>
			<input type="date" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="endDate" value="${project.endDate}"/><br />
	    </div>
	</div>
</fieldset>

<fieldset class="form-group">
	<div class="row">
		<div class="col-sm-4">
			<label><b>Status</b></label>
			<form:select class="form-control" disabled="${project.readOnly}" path="project.status" id="comboStatus" name="comboStatus">
				<form:option value="" label="Selecione" />
		        <form:options items="${project.selectStatus}" itemValue="key" itemLabel="label"/>
			</form:select><br />
	    </div>
	    <div class="col-sm-4">
			<label><b>Orçamento</b></label>
			<div class="form-group">
				<div class="input-icon">
			      <input type="number" step="1" min="1" class="form-control" <c:if test="${project.readOnly}">readonly</c:if> name="budget"
			      		value="${project.budget}" onkeypress="return event.keyCode === 8 || event.charCode >= 48 && event.charCode <= 57">
			      <i>R$</i>
			    </div>
			</div>
	    </div>
	    <div class="col-sm-4">
			<label><b>Risco</b></label>
			<form:select class="form-control" disabled="${project.readOnly}" path="project.risk" id="comboRisco" name="comboRisco">
				<form:option value="" label="Selecione" />
		        <form:options items="${project.selectRisk}" itemValue="key" itemLabel="label"/>
			</form:select><br />
	    </div>
	</div>
</fieldset>