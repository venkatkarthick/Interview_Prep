// What is temporal dead zone?
// Are let and const hoisted?
// Difference between syntax error, reference error and type error?
//--------------------

console.log(a); //udefined
//console.log(b);  //ReferenceError: Cannot access 'b' before initialization
var a=10; 
let b=20; //b is in temporal dead zone until this line is executed. So whenever we try to access b before this line, we get a reference error.
//-------------------

var c=30;
var c=40; // redeclaring var is allowed

let d=50;
//let d=60; // redeclaring let is not allowed, will give SyntaxError
//var d=70; // redeclaring let with var is not allowed, will give SyntaxError
//-------------------

let e;
const f=20; 
e=10;  // assigning/re-assigning value to let is allowed
//const g; // not initializing const is not allowed, will give SyntaxError
//f=30; // reassigning const is not allowed, will give TypeError
//-------------------

// Temporal dead zone is the time between the variable being hoisted and the variable being initialized.

// 1. let and const are hoisted but its memory is allocated at other place than the global window object which cannot be accessed before initialisation.
// 2. Temporal Dead Zone exists until variable is hoisted (usually start of the program because all variables are hoisted or allocated memory before start of the execution of the program) and assigned a value.
// 3. window.variable OR this.variable will not give value of variable defined using let or const.
// 4. We cannot redeclare the same variable with let/const(even with using var the second time). [will get SyntaxError]
// 5. const variable declaration and initialisation must be done on the same line.
// 6. There are three types of error: [1] referenceError {given where variable does not have memory allocation} [2] typeError {given when const type is not treated as constant} [3] syntaxError {when proper syntax(way of writing a statement) is not used}.
// 7. Use const wherever possible followed by let, Use var as little as possible(only if you have to). It helps avoid error.
// 8. Initialising variables at the top is good idea, helps shrinks TDZ to zero.