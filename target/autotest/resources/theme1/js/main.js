jQuery(document).ready(
		function($) {

			$("#sortable").sortable(
					{
						placeholder : "ui-state-highlight",
						helper : 'clone',
						sort : function(e, ui) {
							$(ui.placeholder).html(
									Number($("#sortable > li:visible").index(
											ui.placeholder)) + 1);
						},
						update : function(event, ui) {
							console.log('duplicate!!!!!!')
							var $lis = $(this).children('li');
							$lis
									.each(function() {
										var $li = $(this);
										var newVal = $(this).index() + 1;
										console.log($(this).children(
												'.sortable-number'));
										$(this).find('.sortable-number').text(
												newVal);
										$(this).find('#item_display_order')
												.val(newVal);
									});
						}
					});
			$(document).find("#sortable").disableSelection();
			$(document).on('click','.header-step-label',function(e) {
				$(this).next('.show-grid').find('.content-step').toggle('fast');
			});
			$(document).on('click','.ui-state-default .glyphicon-remove',function(){
				$(this).closest('li').remove();
				var $lis = $( "#sortable" ).children('li');
				$lis.each(function() {
					var $li = $(this);
					var newVal = $(this).index() + 1;
					console.log($(this).children('.sortable-number'));
					$(this).find('.sortable-number').text(newVal);
					$(this).find('#item_display_order').val(newVal);
				});
			});
			$(document).on('click','#sortable .add-button',function(){
				var liStep=$("#data-clone li").clone();
				$( liStep ).insertBefore($(this).closest('li'));
				$(document).find( "#sortable" ).sortable( "refreshPositions" );
				var $lis = $(document).find( "#sortable" ).children('li');
				$lis.each(function() {
					var $li = $(this);
					var newVal = $(this).index() + 1;
					console.log($(this).children('.sortable-number'));
					$(this).find('.sortable-number').text(newVal);
					$(this).find('#item_display_order').val(newVal);
				});
				
			});
			var testId=1;
			$(document).on('click','.sidenav .btn-default',function(){
				console.log("xx");
				$('#list-testcase').append("<li><a href='#'>Test case "+testId+"</a></li>");
				testId++;
				
			});
			$(document).on('click','.sidenav ul li a',function(){
				console.log("xx12",$(this).text());
				
				$('#name-testcase').text($(this).text());
			});
			$(document).on('click','#save-testcase',function(){
				var listStep = [];
						var $lis = $(document).find('ul#sortable li');
						var i = 0;
						$lis
								.each(function() {
									var $li = $(this);
									var step = {};
									step.action = $li.find('.action-form')
											.find('select').val();
									step.elementType = $li.find(
											'.element-type-form')
											.find('select').val();
									step.elementDefine =$li.find(
											'.element-define-form').find(
											'input').val();
									step.valueEnter = $li.find(
											'.value-enter-form').find('input')
											.val();
									step.combineMultiAction='N';
									if(i==($lis.length-1))
										step.combineMultiAction='N';
									console.log(step);
									listStep[i] = step;
									i++;
								});
						console.log(listStep);
						var send = {};
						//send.lstStep = listStep;
						 send.lstStep=JSON.stringify(listStep);
						 //send.url="https://www.youtube.com/";
						// send.url="https://www.google.com/";
						 send.url=$('#url-web').val();
						 send.ip=$('#ip-computer').val();
						 send.browser=$('input[name=browser]:checked').val();
						 send.desc=$('#testcase-desc').text();
						 send.name=$('#name-testcase').text();
						// console.log(JSON.parse(listStep))
						console.log(send)
						$.ajax({
							url : "saveTestcase",
							type : "GET",
							contentType : "application/json",
							data : send,

							success : function(msg) {
								$('.answer').html(msg);
							}
						});
			});
			$(document).find("#run-testcase").click(
					function(event) {
						var listStep = [];
						var $lis = $(document).find('ul#sortable li');
						var i = 0;
						$lis
								.each(function() {
									var $li = $(this);
									var step = {};
									step.action = $li.find('.action-form')
											.find('select').val();
									step.elementType = $li.find(
											'.element-type-form')
											.find('select').val();
									step.elementDefine =$li.find(
											'.element-define-form').find(
											'input').val();
									step.valueEnter = $li.find(
											'.value-enter-form').find('input')
											.val();
									step.combineMultiAction='N';
									if(i==($lis.length-1))
										step.combineMultiAction='N';
									console.log(step);
									listStep[i] = step;
									i++;
								});
						console.log(listStep);
						var send = {};
						//send.lstStep = listStep;
						 send.lstStep=JSON.stringify(listStep);
						 //send.url="https://www.youtube.com/";
						// send.url="https://www.google.com/";
						 send.url=$('#url-web').val();
						 send.ip=$('#ip-computer').val();
						 send.browser=$('input[name=browser]:checked').val();
						// console.log(JSON.parse(listStep))
						console.log(send)
						$.ajax({
							url : "runTestcase",
							type : "GET",
							contentType : "application/json",
							data : send,

							success : function(msg) {
								$('.answer').html(msg);
							}
						});
					});

		});
