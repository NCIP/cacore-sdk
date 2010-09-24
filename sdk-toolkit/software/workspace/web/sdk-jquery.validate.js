	var intRegex = /^-?\d+$/i;
	var positiveIntRegex = /^\d+$/i;
	var charRegex = /^\w$/i;
	var dateRegex = /^(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|20)\d\d$/i;	
	var mailtoUriRegex = /^mailto:((([a-z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))(\?.*)*$/i;
	var telUriRegex = /^(mailto|tel|x-text-tel|x-text-fax):(\/\/)*(\+\d)*\s*(\(\d{3}\)\s*|\d{3}\s*|\d{3}-\s*)*\d{3}(-{0,1}|\s{0,1})\d{2}(-{0,1}|\s{0,1})\d{2}$/i;
	var telUrlRegex = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&amp;'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&amp;'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&amp;'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&amp;'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&amp;'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
	var timestampRegex = /^([0-1][0-9]-[0-3][0-9]-[0-9]{4}) ?[0-2][0-9]:[0-5][0-9]:[0-5][0-9]$/i;
	
	$.validator.addMethod("PQV_precision", function(value, element) {
        return this.optional(element) || positiveIntRegex.test(value);
    }, "PQV precision should contain positive digits.");
        
    $.validator.addMethod("int_long", function(value, element) {
        return this.optional(element) || intRegex.test(value);
    }, "Integer and Long data types should contain only an optional minus sign, followed by digits.");
    
    $.validator.addMethod("date", function(value, element) {
        return this.optional(element) || dateRegex.test(value);
    }, "Date data type attributes should be specified using the syntax: mm-dd-yyyy.");
          
    $.validator.addMethod("char", function(value, element) {
        return this.optional(element) || charRegex.test(value);
    }, "Character data type attributes should only contain a single alphanumeric character including the underscore.");
    
    $.validator.addMethod("INT_value", function(value, element) {
        return this.optional(element) || intRegex.test(value);
    }, "INT value should contain only an optional minus sign, followed by digits.");
            
    $.validator.addMethod("TEL_value", function(value, element) {
        return this.optional(element) || mailtoUriRegex.test(value) || telUriRegex.test(value) || telUrlRegex.test(value);
    }, "TEL value should start with the mailto:, tel:, x-text-tel:, x-text-fax: URI scheme, or a valid URL.");
        
    $.validator.addMethod("TEL_PERSON_value", function(value, element) {
        return this.optional(element) || telUriRegex.test(value) || mailtoUriRegex.test(value);
    }, "TEL PERSON value should start with the mailto:, tel:, x-text-tel:, or x-text-fax: URI scheme, followed by a valid email address or phone number.");
       
    $.validator.addMethod("TEL_PHONE_value", function(value, element) {
        return this.optional(element) || telUriRegex.test(value);
    }, "TEL PHONE value should start with the tel:, x-text-tel:, or x-text-fax: URI scheme, followed by a valid phone number.");
    
    $.validator.addMethod("TEL_EMAIL_value", function(value, element) {
        return this.optional(element) || mailtoUriRegex.test(value);
    }, "TEL EMAIL value should start with \"mailto:\" URI scheme, followed by a valid email address.");
    
    $.validator.addMethod("TS_value", function(value, element) {
        return this.optional(element) || timestampRegex.test(value);
    }, "TS timestamp value should follow the syntax: mm-dd-yyyy hh:mm:ss");
	    
	$(document).ready(function(){
		$("#Result").validate();
	});
	
	function enableValidation()
    {
    	//alert("Enabling Validation");
		$("#Result").validate();
    };
    
    function setTabIndex()
    {
    	//alert("Setting Tab Order");
    	var tabindex = 1;
    	$('input,select').each(function() {
    	    	if (this.type != "hidden") {
    	    	    	var $input = $(this);
    	    	    	$input.attr("tabindex", tabindex);
    	    	    	tabindex++;
				}
		});
		
		//Set focus on first input field
		$(":input:visible:first").focus();
	};
	