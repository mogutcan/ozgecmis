/**
 * 
 */
$(document).ready(function(){
			$('#registerHere input').hover(function()
			{
			$(this).popover('show')
		});
			$("#registerHere").validate({
				
				// Kurallarý burda tanýmla input text name ile
				// Alan doldurulmasý zorunlu bir alan mý?
				rules:{
					user_name:"required",
					user_email:{
							required:true,
							email: true
						},
					dogumtarihi:"required",
					secim01:"required"
			
				},
				
				//  Hatalý bilgi girildiðinde verilecek hata mesajlarý
				messages:{
					user_name:"Ad giriniz",
					user_email:{
						required:"E-mail giriniz",
						email:"Geçersiz e-mail adresi"
					},
					dogumtarihi:"Gün/Ay/Yýl þeklinde giriniz",
					secim01:"Sýfýr kaç"
				},
				errorClass: "help-inline",
				errorElement: "span",
				highlight:function(element, errorClass, validClass) {
					$(element).parents('.control-group').addClass('error');
				},
				unhighlight: function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
		
		});
