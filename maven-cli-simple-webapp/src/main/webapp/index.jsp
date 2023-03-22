<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>My App</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <script>
      var RESULT = "";
      var INPUTS = "";
  </script>  
  
  <script>
    // Wait for the DOM to be ready
    document.addEventListener("DOMContentLoaded", function() {
      // Find the form element



      const form = document.querySelector("#contact");

      // Listen for the form's "submit" event
      form.addEventListener("submit", function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Collect the input values and put them in an array
        const inputs = Array.from(document.querySelectorAll("#contact input[type=text]"));
        const texts = inputs.map(input => input.value.trim()).filter(input=>!!input);
        console.log(texts)
        $.ajax({
          type: "POST",
          url: "simple",
          data: { texts: JSON.stringify(texts) },
          success: function(response) {
            console.log("Server response:", response);
            document.querySelector("#result").textContent = response.result;
            document.querySelector("#nb_texts").textContent = response.nb_texts;
            INPUTS = response.texts;
            RESULT = response.result;
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX error:", textStatus, errorThrown);
          }
        });
      });
    });
  </script>  


  <script>
    // Wait for the DOM to be ready
    document.addEventListener("DOMContentLoaded", function() {
      // Find the form element
      const saveBtn = document.querySelector("#save-btn");

      // Listen for the form's "submit" event
      saveBtn.addEventListener('click', function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        $.ajax({
          type: "POST",
          url: "simple/save",
          data: { result: JSON.stringify(RESULT),texts: JSON.stringify(INPUTS) },
          success: function(response) {
            console.log("Server response:", response);
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX error:", textStatus, errorThrown);
          }
        });
      });
    });
  </script>
</head>

<body>
  <div class="container">
    <h1>My App</h1>
    <form id="contact" method="post">
      <div><h3>Enter some texts</h3></div>
      <div class="row" id="my-inputs">
        <!-- Iterate over the list and put each string into a span element -->
        <c:forEach items="${requestScope.texts}" var="text" varStatus="status">
          <div><label for="text${status.index}">Text ${status.index + 1}</label><input type="text" class="form-control" name="text${status.index}" id="text${status.index}" placeholder="text..." value="${text}"></div>
        </c:forEach>
      </div>
      <div><input type="submit" value="Submit"></div>
    </form>
    <div>
      <h3>Combined text</h3>
      <p id="result"></p>
      <p id="nb_texts"></p>
    </div>
    <div>
      <button id="add-btn" type="button" class="btn btn-primary">Add text</button>
      <button id="save-btn" type="button" class="btn btn-primary">Save results</button>
    </div>
  </div>
</body>

<script>
  // Get the div where the new inputs will be added
  var inputsDiv = document.getElementById('my-inputs');

  // Get the add button
  var addBtn = document.getElementById('add-btn');

  // Add a click event listener to the add button
  addBtn.addEventListener('click', function() {
    // Counter to keep track of the number of new inputs
    var inputCounter = inputsDiv.children.length;
    
    // Create a new div element
    var newDiv = document.createElement('div');

    // Create a new label element
    var newLabel = document.createElement('label');
    newLabel.setAttribute('for', 'text' + inputCounter);
    newLabel.textContent = 'Text ' + (inputCounter + 1);

    // Create a new input element
    var newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('class', 'form-control');
    newInput.setAttribute('name', 'text' + inputCounter);
    newInput.setAttribute('id', 'text' + inputCounter);
    newInput.setAttribute('placeholder', 'text...');

    // Add the label and input to the new div
    newDiv.appendChild(newLabel);
    newDiv.appendChild(newInput);

    // Add the new div to the inputs div
    inputsDiv.appendChild(newDiv);

    // Increment the input counter
    inputCounter++;
  });

</script>

</html>
