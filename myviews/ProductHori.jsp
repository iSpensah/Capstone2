<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

#profile_leftPanel {
  width: 200px;
  float: left;
  border-right: 0px solid #666;
  margin-right: 20px;
}
.horizontal {
  display: inline;
  float:left;
  padding:10px 5px;
  border:1px solid #dddddd;
  margin-right:5px;
  }
  
  .slidecontainer {
  width: 100%; /* Width of the outside container */
}

/* The slider itself */
.slider {
  -webkit-appearance: none;  /* Override default CSS styles */
  appearance: none;
  width: 100%; /* Full-width */
  height: 25px; /* Specified height */
  background: #d3d3d3; /* Grey background */
  outline: none; /* Remove outline */
  opacity: 0.7; /* Set transparency (for mouse-over effects on hover) */
  -webkit-transition: .2s; /* 0.2 seconds transition on hover */
  transition: opacity .2s;
}

/* Mouse-over effects */
.slider:hover {
  opacity: 1; /* Fully shown on mouse-over */
}

/* The slider handle (use -webkit- (Chrome, Opera, Safari, Edge) and -moz- (Firefox) to override default look) */
.slider::-webkit-slider-thumb {
  -webkit-appearance: none; /* Override default look */
  appearance: none;
  width: 25px; /* Set a specific slider handle width */
  height: 25px; /* Slider handle height */
  background: #04AA6D; /* Green background */
  cursor: pointer; /* Cursor on hover */
}

.slider::-moz-range-thumb {
  width: 25px; /* Set a specific slider handle width */
  height: 25px; /* Slider handle height */
  background: #04AA6D; /* Green background */
  cursor: pointer; /* Cursor on hover */
}
body {
background-color: coral;
}
</style>
 
</head>
<body >
 <!-- Start of left panel -->
 <div id="profile_leftPanel">
   <form action="viewFilterProduct">
	<input type="checkbox" name= "All" value="All">All Categories
	<br>
	<c:forEach var="categoryname" items="${procat}">
        <input type="checkbox" name="cat" value="${categoryname}"/> <label>${categoryname} <label/>
        <br>
    </c:forEach>   	
    <br>
<input type="Submit" value="FILTER"/>
 <br>
 
 <!-- start of Price Slider -->
 <div class="slidecontainer">
 <p> <p/><input type="range" min="1" max="10000" value="10000" name="pprice" class="slider" id="myRange"> 
  <p>Price Range: <span id="demo"></span></p>
</div>
 <!-- End of Price Slider -->
</form>
  </div>
 <!-- End of Left Panel -->

 
<script>
var slider = document.getElementById("myRange");
var output = document.getElementById("demo");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}
</script>

<!--  Start of List of PRoducts -->
<div>
<ul>
<c:forEach var="product" items="${allproducts}">
  <li class="horizontal" style="display: inline;">
    <div class="product">
      <div class="image">
        <img class="img-responsive" src="${product.imagepath}" width="230" height = "230"/>
      </div>
      <div class="description">
        <h4 class="productname">${product.productname} /${product.productid}</h4>
      </div>
      <div class="price">
       <h4>Price: <span>${product.pprice}</span></h4>
       <p>Stocks <span>${product.QOH}</span></p>
       <p>Category : <span>${product.categoryname}</span></p>
        <input type="button" class="btn btn-primary btn-sm" value="Details" />
        <a href="addtocart/${product.productid}"><input type="button" class="btn btn-primary btn-sm" value="Add to Cart" /><a/>
      </div>
    </div>
  </li>
</c:forEach>
</ul>
</div>
<!--  ENd of  List of PRoducts -->
</body>
</html>