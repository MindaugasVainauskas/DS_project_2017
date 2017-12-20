<html>
<head>
	<title>Distributed Systems Project</title>
</head>
<body>
	<h4>Enter word to look for in dictionary.</h4>
	<form id="wordInputForm">
		
		<input type="text" value="word" placeholder="Enter a word"/>
		<button>Submit</button>
	</form>
	
	<table>
		<thead>
			<tr>
				<th>Word</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>{{ word }}</td>
				<td>{{ explanation }}</td>
			</tr>
		</tbody>
	</table>

</body>
</html>