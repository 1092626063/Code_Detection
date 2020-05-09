<%@ page contentType = "text/html; charset = UTF-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>:: PENNY ::</title>
		<!-- Description, Keywords and Author -->
		<meta name="description" content="Your description">
		<meta name="keywords" content="Your,Keywords">
		<meta name="author" content="ResponsiveWebInc">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,400,600' rel='stylesheet' type='text/css'>	 
		<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700,300italic' rel='stylesheet' type='text/css'>
		
		
		<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- Font awesome CSS -->
		<link href="css/font-awesome.min.css" rel="stylesheet">	
		<!-- Animate CSS -->
		<link href="css/animate.min.css" rel="stylesheet">
		<!-- Custom CSS -->
		<link href="css/style.css" rel="stylesheet">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
		<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
	</head>
	
	<body>
		
		<div class="outer">
		
			<!-- 左侧边栏 Start-->
			<!-- 平板电脑和手机的菜单 -->
			<div class="navigation">
				<a href="#">菜单</a>
			</div>
			<div class="sidebar">
				<!-- 标志 -->
				<div class="logo text-center">
					<h1><a href="compare.jsp">相似度对比</a></h1>
					<p>Compared</p>
				</div>
				<div class="sidebar-search">
					<form>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="搜索 ">
							<span class="input-group-btn">
								<button class="btn btn-danger" type="button"><i class="icon-search"></i></button>
							</span>
						</div>
					</form>
				</div>
				<!-- 导航菜单 -->
				<ul class="list-unstyled list">
					<li><a href="#home" class="anchorLink"><i class="icon-home scolor"></i> 主页</a></li>
					<li><a href="#.feature" class="anchorLink"><i class="icon-road scolor"></i> 特征</a></li>
					<li><a href="#.testimonial" class="anchorLink"><i class="icon-info scolor"></i> 介绍</a></li>
					<li><a href="#.team" class="anchorLink"><i class="icon-user scolor"></i> 关于我们</a></li>
					<li><a href="#.contact" class="anchorLink"><i class="icon-envelope scolor"></i> 联系我们</a></li>
			<!--	    <li><a href="#.service" class="anchorLink"><i class="icon-retweet scolor"></i> 服务</a></li>
					<li><a href="#.ptable" class="anchorLink"><i class="icon-gift scolor"></i> Price Table</a></li>  
					<li class="dropdown">
							<a data-toggle="dropdown" href="#"><i class="icon-plus-sign scolor"></i> Dropdown <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" >
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
					</li>
				</ul>

				<!-- 社交媒体链接 -->
				<div class="social">
					<a href="#" class="facebook"><i class="icon-facebook"></i></a>
					<a href="#" class="twitter"><i class="icon-twitter"></i></a>
					<a href="#" class="google"><i class="icon-google-plus"></i></a>
					<a href="#" class="linkedin"><i class="icon-linkedin"></i></a>
				</div>
			</div>
			
			<!-- 左侧边栏 End -->
			
			
			<!-- 主页 Start -->
			
			<div class="main">
			
				<div id="carousel-example-generic" class="carousel slide carousel-fade">
				   
					<!-- Wrapper for slides -->
					
					<div class="carousel-inner">
						<!-- Carousel item start -->
						<div class="item active">
							<!-- Carousel background images -->
							<img src="img/3.jpg" class="img-responsive" alt="" />
							<div class="carousel-caption">
								<div class="row">
									<div class="col-md-6">
										<!-- Images for carousel foreground -->
										<img src="img/s2.png" alt="" class="img-responsive" />
									</div>
									<div class="col-md-6">
										<!-- Carousel caption -->
										<div class="caption-content">
											<h3>Bonorum et Malorum</h3>
											<p>It is a long established fact that a reader will be distracted by Lorem Ipsum passage, and going the readable content oits layout.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
				  
						<div class="item">
							<img src="img/3.jpg"  class="img-responsive" alt=""/>
							<div class="carousel-caption">
								<div class="row">
									<div class="col-md-6">
										<img src="img/s3.png" alt="" class="img-responsive" />
									</div>
									<div class="col-md-6">
										<div class="caption-content">
											<h3>Extremes of Good</h3>
											<p>The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using making it look like readable English.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
				  
						<div class="item">
							<img src="img/3.jpg"  class="img-responsive" alt=""/>
							<div class="carousel-caption">
								<div class="row">
									<div class="col-md-6">
										<img src="img/s1.png" alt="" class="img-responsive" />
									</div>
									<div class="col-md-6">
										<div class="caption-content">
											<h3>Contrary to popular</h3>
											<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form even slightly believable.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Controls -->

					<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
						<span class="icon-prev"></span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
						<span class="icon-next"></span>
					</a>
				</div>
				
				<!-- Wrapper for slides End -->
				
				
				
				<div class="container">
				
					<!-- 网页简介 start-->
					
					<div class="hero">
						<div class="row">
							<div class="col-md-12">
								<h2>It's Lorem ipsum dolor sit amet consectetur</h2>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec consectetur adipiscing elit tristique est consectetur adipiscing elit tristique sit amet diam interdum semper. Accumsan id.</p>
								
							</div>
						</div>
					</div>
					<hr />
					<!-- 网页简介 End -->
					
					<!-- 特征 Start -->
					
					<div class="feature">
						<h3>特征</h3>
						<div class="bor hidden-xs"></div>
						<div class="row">
							<div class="col-md-3 col-xs-6">
								<!-- Feature item -->
								<div class="feature-item animated">
									<i class="icon-gears"></i>
									<!-- Feature item heading and description -->
									<h4>Similique Sunt Sin</h4>
									<p>Circumstances occur in which toil him some great pleasure.</p>
								</div>
							</div>
							<div class="col-md-3 col-xs-6">
								<div class="feature-item animated">
									<i class="icon-refresh"></i>
									<h4>Harum Et Quidem</h4>
									<p>Et harum quidem rerum facilis est et expedita distinctio.</p>
								</div>
							</div>
							<div class="col-md-3 col-xs-6">
								<div class="feature-item animated">
									<i class="icon-unlink"></i>
									<h4>Voluptatum Deleniti Atque </h4>
									<p>Voluptates repudiandae sint et iae non recusandae.</p>
								</div>
							</div>
							<div class="col-md-3 col-xs-6">
								<div class="feature-item animated">
									<i class="icon-puzzle-piece"></i>
									<h4>Blinded By Desire</h4>
									<p>Cases are perfectly simple and easy to distinguish.</p>
								</div>
							</div>
						</div>
					</div>
					<hr />
					
					<!-- 特征 End -->
					
					<!-- 介绍 Start -->
					
					<div class="testimonial">
						<h3>介绍</h3>
						<div class="bor hidden-xs"></div>
						<div class="row">
							<div class="col-md-4">
								
								<div class="testimonial-content animated">
									
									<h4><img src="img/user-xs.jpg" alt="" class="img-responsive img-circle"/>黄树琪<span>, Manager</span></h4>
									
									<blockquote>
										<p>Sed ut perspiciatis unde omnis iste natust error sit vol accusantium doloremque and laudantium, totam rem aperiam, eaque an ipsa quae abe illo explicabo. An oiuk nesciunt.</p>
									</blockquote>
								</div>
							</div>
							<div class="col-md-4">
								<div class="testimonial-content animated">
									<h4><img src="img/user-xs.jpg" alt="" class="img-responsive img-circle"/>应尚威<span>, Technical Director</span></h4>
									<blockquote>
										<p>On the other hand, we denounce with righteous indignation dislike men who are so beguiled and demoralized by the charms of pleasu of the moment, that are bound to ensue.</p>
									</blockquote>
								</div>
							</div>
							<div class="col-md-4">
								<div class="testimonial-content animated">
									<h4><img src="img/user-xs.jpg" alt="" class="img-responsive img-circle"/>沈鑫杰<span>, Project Director</span></h4>
									<blockquote>
										<p>Welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of  that have to be the moment and annoyances accepted.</p>
									</blockquote>
								</div>
							</div>
						</div>
					</div>
					<hr />
					
					<!-- 介绍 End -->
					
					<!-- 团队简介 Start -->
					<div class="team">
						<h3>关于我们</h3>
						<div class="bor hidden-xs"></div>
						<div class="row">
							<div class="col-md-3 col-sm-6">
								<!-- Team member's profile  -->
								<div class="thumbnail team-profile">
									<img src="img/user.jpg" alt="" class="img-responsive img-circle" />
									<!-- Member's name and designation -->
									<h5>黄树琪</h5>
									<span>Manager</span>
									<div class="social-link">
										<a href="#" class="facebook"><i class="icon-facebook"></i></a>
										<a href="#" class="google"><i class="icon-google-plus"></i></a>
										<a href="#" class="twitter"><i class="icon-twitter"></i></a>
										<a href="#" class="linkedin"><i class="icon-linkedin"></i></a>
										<a href="#" class="skype"><i class="icon-skype"></i></a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="thumbnail team-profile">
									<img src="img/user.jpg" alt="" class="img-responsive img-circle" />
									<h5>应尚威</h5>
									<span>Technical Director</span>
									<div class="social-link">
										<a href="#" class="facebook"><i class="icon-facebook"></i></a>
										<a href="#" class="google"><i class="icon-google-plus"></i></a>
										<a href="#" class="twitter"><i class="icon-twitter"></i></a>
										<a href="#" class="linkedin"><i class="icon-linkedin"></i></a>
										<a href="#" class="skype"><i class="icon-skype"></i></a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="thumbnail team-profile">
									<img src="img/user.jpg" alt="" class="img-responsive img-circle" />
									<h5>沈鑫杰</h5>
									<span>Project Director</span>
									<div class="social-link">
										<a href="#" class="facebook"><i class="icon-facebook"></i></a>
										<a href="#" class="google"><i class="icon-google-plus"></i></a>
										<a href="#" class="twitter"><i class="icon-twitter"></i></a>
										<a href="#" class="linkedin"><i class="icon-linkedin"></i></a>
										<a href="#" class="skype"><i class="icon-skype"></i></a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="thumbnail team-profile">
									<img src="img/user.jpg" alt="" class="img-responsive img-circle" />
									<h5>胡宸源</h5>
									<span>Art Director</span>
									<div class="social-link">
										<a href="#" class="facebook"><i class="icon-facebook"></i></a>
										<a href="#" class="google"><i class="icon-google-plus"></i></a>
										<a href="#" class="twitter"><i class="icon-twitter"></i></a>
										<a href="#" class="linkedin"><i class="icon-linkedin"></i></a>
										<a href="#" class="skype"><i class="icon-skype"></i></a>
									</div>
								</div>
							</div>
						</div>	
					</div>
					<hr />
					
					<!-- 团队简介 End --> 
						
					<!-- Services start 
					<div class="service">
						<h3>Service</h3>
						<div class="bor hidden-xs"></div>
						<div class="row">
							<div class="col-md-3 col-sm-6 col-xs-6">
								
								<div class="service-item animated">
									<i class="icon-camera skyblue"></i>
									
									<h4><a href="#">Neque perspiciatis</a></h4>
									<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia mollit anim id ests.</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-6">
								<div class="service-item animated">
									<i class="icon-envelope nblue"></i>
									<h4><a href="#">Finibus perspiciatis</a></h4>
									<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium totam remo.</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-6">
								<div class="service-item animated">
									<i class="icon-download blue"></i>
									<h4><a href="#">Cicero perspiciatis</a></h4>
									<p>Nor again is there anyone who loves or pursues or desires to obtain pain of itself pain.</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-6">
								<div class="service-item animated">
									<i class="icon-gamepad green"></i>
									<h4><a href="#">Malorum perspiciatis</a></h4>
									<p>At vero eos et accusamus et iusto odio dignissimos qui blanditiis praesentium itate none.</p>
								</div>
							</div>
						</div>
					</div>
					<hr />
					<!-- Services End -->
					
					
					
					<!-- Price Table Start 
					
					<div class="ptable">
						<h3>Pricing Table</h3>
						<div class="bor hidden-xs"></div>
						<div class="row">
							<div class="col-md-3">
								
								<div class="ptable-content">
									
									<h4>STARTER</h4>
									<p>Some thighs goes here</p>
								
									<span class="pcost">$10.50/m</span>
								
									<ul class="list">
										<li>1GB Storage</li>
										<li>100GB Bandwidth</li>
										<li>24/7 Support</li>
									</ul>
								
									<a href="#" class="btn btn-info">Buy It Now</a>
								</div>
							</div>
							<div class="col-md-3">
								
								<div class="phighlight ptable-content">
									<h4>BUSINESS</h4>
									<p>Some thighs goes here</p>
									<span class="pcost">$30.50/m</span>
									<ul class="list">
										<li>1GB Storage</li>
										<li>100GB Bandwidth</li>
										<li>24/7 Support</li>
									</ul>
									<a href="#" class="btn btn-info">Buy It Now</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="ptable-content">
									<h4>ENTERPRISE</h4>
									<p>Some thighs goes here</p>
									<span class="pcost">$50.50/m</span>
									<ul class="list">
										<li>1GB Storage</li>
										<li>100GB Bandwidth</li>
										<li>24/7 Support</li>
									</ul>
									<a href="#" class="btn btn-info">Buy It Now</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="ptable-content">
									<h4>MNC</h4>
									<p>Some thighs goes here</p>
									<span class="pcost">$150.50/m</span>
									<ul class="list">
										<li>1GB Storage</li>
										<li>100GB Bandwidth</li>
										<li>24/7 Support</li>
									</ul>
									<a href="#" class="btn btn-info">Buy It Now</a>
								</div>
							</div>
						</div>
					</div>
					<hr />
					<!-- Price Table End -->
					
					
					<!-- 联系我们 Start -->
					
					<div class="contact">
						<h3>联系我们</h3>
						<div class="bor hidden-xs"></div>
						<div class="contact-details">
							<!-- Contact form  -->
							<h4>联系我们</h4>
							<form role="form" class="form">
								<div class="form-group">
									<!-- Name input box -->
									<label for="exampleInputEmail1">姓名</label>
									<input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter Name">
								</div>
								<div class="form-group">
									<!-- Email input box -->
									<label for="exampleInputPassword1">邮箱</label>
									<input type="email" class="form-control" id="exampleInputPassword1" placeholder="Enter Email">
								</div>
								<div class="form-group">
									<!-- Message input box -->
									<label>内容</label>
									<textarea class="form-control" rows="3"></textarea>
								</div>
								
									<!-- Submit and reset button -->
								<button type="submit" class="btn btn-default">提交</button>
								<button type="reset" class="btn btn-default">重写</button>
							</form>
							<!-- Address -->
							<h5>地址</h5>
							<p><span>锦城街道 武肃街666号,</span><br />
								<p><span>浙江临安.</span><br />
								<p><span>@浙江农林大学</span><br />
								P:+1234 567 890.</p>
							<div class="c-social">
								<a href=""><i class="icon-facebook"></i></a>
								<a href=""><i class="icon-google-plus"></i></a>
								<a href=""><i class="icon-twitter"></i></a>
								<a href=""><i class="icon-linkedin"></i></a>
							</div>
						</div>
						
						<!-- 谷歌地图，以帮助找到地址 -->
						<div class="google-map">
								<iframe src="map.jsp"  frameBorder=0 height=600 width=1000 scrolling=no></iframe>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<!-- 联系我们 End -->
					
					<!-- 底部 -->
				<footer>
					<div class="row">
						<div class="col-md-12">
							<hr />
							<br />
							<p class="text-center">浙A2-20110026 浙ICP备11017770号 Copyright &copy; 2016-2017 浙江A&F信息工程学院版权所有 - Collect from <a href="http://ie.zafu.edu.cn/">浙江农林大学信息工程学院</a></p>
							<br />
						</div>
					</div>
				</footer>
				
				</div>
				
				
				
			</div>	<!-- This end div for main class -->
		</div>
		
		<div class="clearfix"></div>
		
		
		<!-- Scroll to top -->
      <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 

		
		<!-- Javascript files -->
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		<!-- Bootstrap JS -->
		<script src="js/bootstrap.min.js"></script>
		<!-- jquery Anchor JS -->
		<script src="js/jquery.arbitrary-anchor.js"></script>
		<!-- jQuery way points -->
		<script src="js/waypoints.min.js"></script>
		<!-- Respond JS for IE8 -->
		<script src="js/respond.min.js"></script>
		<!-- HTML5 Support for IE -->
		<script src="js/html5shiv.js"></script>
		<!-- Custom JS -->
		<script src="js/custom.js"></script>
	</body>	
</html>