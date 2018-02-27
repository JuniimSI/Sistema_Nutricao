(function($) {
	$(function() {
		$(document).ready(function() {
			$('select').material_select();
		});
		$('.carousel.carousel-slider').carousel({fullWidth: true});

		
		// move next carousel
		   $('.moveNextCarousel').click(function(e){
		      e.preventDefault();
		      e.stopPropagation();
		      $('.carousel').carousel('next');
		   });

		   // move prev carousel
		   $('.movePrevCarousel').click(function(e){
		      e.preventDefault();
		      e.stopPropagation();
		      $('.carousel').carousel('prev');
		   });
		   
		$('.modal').modal({
			dismissible : true, // Modal can be dismissed by clicking outside of
								// the modal
			opacity : .5, // Opacity of modal background
			inDuration : 300, // Transition in duration
			outDuration : 200, // Transition out duration
			startingTop : '4%', // Starting top style attribute
			endingTop : '10%', // Ending top style attribute
		});
		$(document).ready(function() {
			$('.materialboxed').materialbox();
		});

		var receitas = [];
		var autocomplete = $(".nome-autocomplete")
				.autocomplete(
						{
							multiple : {
								enable : false
							},
							cacheable : false,
							getData : function(value, callback) {

								if (!isNaN(parseFloat(value))) {
									var url = _context
											+ "/receita/api/buscarProdutoCadastrado/"
											+ value;
									var token = $("meta[name='_csrf']").attr(
											"content");

									$
											.ajax({
												dataType : "json",
												url : url,
												type : 'post',
												headers : {
													"X-CSRF-TOKEN" : token
												},
												success : function(
														responseReceitas) {
													receitas = responseReceitas.object;
													if (responseReceitas.object != null) {
														var data = responseReceitas.object
																.map(function(m) {

																	var receitas = m.codigos;

																	return {
																		id : m.id,
																		text : m.nome
																				+ " | "
																				+ m.unidadeMedida.nome
																	};

																});
														callback(value, data);
													} else {
														var data = [];
														callback(value, data);
													}

												}
											});
								}
								if (value.length > 3) {
									var url = _context
											+ "/receita/api/buscarProdutoCadastrado/"
											+ value;
									var token = $("meta[name='_csrf']").attr(
											"content");

									$
											.ajax({
												dataType : "json",
												url : url,
												type : 'post',
												headers : {
													"X-CSRF-TOKEN" : token
												},
												success : function(
														responseReceitas) {
													receitas = responseReceitas.object;
													if (responseReceitas.object != null) {
														var data = responseReceitas.object
																.map(function(m) {

																	var receitas = m.codigos;

																	return {
																		id : m.id,
																		text : m.nome
																				+ " | "
																				+ m.unidadeMedida.nome
																	};

																});
														callback(value, data);
													} else {
														var data = [];
														callback(value, data);
													}

												}
											});
								}
							},
							onSelected : function(item) {
								var id = item.id;
								if (receitas != null) {
									var estoqueSetor = materiais
											.filter(function(m) {
												return m.id === id;
											})[0];
								}
							}
						});

		$('#adicionar')
				.click(
						function() {
							var nome = document.getElementById("produtoName").value;
							// var telefone =
							// document.getElementById("txttelefone").value;
							// var email =
							// document.getElementById("txtemail").value;
							$(".table1")
									.append(
											' <tr >   <td><a th:text='
													+ nome
													+ '"></a></td>   <td th:text="15">15</td>    <td th:text="Pacote">Pacote</td>   </tr>')
						});

		$('.button-collapse').sideNav();

		$('.button-collapse-minimalize').sideNav({
			menuWidth : 300, // Default is 300
			edge : 'right', // Choose the horizontal origin
			closeOnClick : true, // Closes side-nav on <a> clicks, useful for
									// Angular/Meteor
			draggable : true, // Choose whether you can drag to open on touch
								// screens,
			onOpen : function(el) { /* Do Stuff */
			}, // A function to be called when sideNav is opened
			onClose : function(el) { /* Do Stuff */
			}, // A function to be called when sideNav is closed
		});
		$('.collapsible').collapsible();
	}); // end of document ready
})(jQuery); // end of jQuery name space
