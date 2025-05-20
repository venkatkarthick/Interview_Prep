// Functions and their variabe environment. Whenever a function is created, a new execution context is created for that function. 
// This EC's memory component is called variable environment.
var x=1;
a();
b();

function a(){
    var x=2;
    console.log(x);
}
function b(){
    var x=3;
    console.log(x);
}
// Variables are first referred in the local scope and then referred in the global scope.


//Simple program in JS is empty file.

// window object is created by the JS engines of the respective browsers when global execution context is created.
	// • whenever an execution context is created a "this" variable is also created.
	// • at the global level "this" points to the global object( window object in case of browsers).
	// • anything that is not inside a function is the "global space".
	// • whenever we create any variables or functions in the "global space", they get attached to the global object( window object in case of browsers).
	
	// so to access the variables/function defined in the global space , 
	// we can use any of the below:

        var a=10;  //All the variables declared in global space are attached to the window object.
        function c(){
            var x=20; //This is not in the global space.
        }
		console.log(window.a);
		console.log(a);
		console.log(this.a)             //at the global space level, where this points to the window object