function x() {
    var i=10;
    setTimeout(function() {
        console.log(i);
    }, 1000); // function inside setTimeout forms a closure with x.
    console.log("Exiting x");
}
x();
//------------------

// Print 1 to 5 for 1 to 5 second
console.log("Print 1 to 5 for 1 to 5 second - Failed!!!");
function print1() {
    for (var i=1; i<=5; i++) {
        setTimeout(function() {
            console.log("1-"+i); // This will print 6 five times because we used var which refernces the same memory location for i.
        }, i*1000);
    }
}
print1();

console.log("Print 1 to 5 for 1 to 5 second ");
function print2() {
    for (let j=1; j<=5; j++) {
        setTimeout(function() {
            console.log("2-"+j); // We can use let as it is block scoped and fn forms a closure with the block.
        }, j*1000);
    }
}
print2();

console.log("Print 1 to 5 for 1 to 5 second - If var should be used");
function print3() {
    for (var k=1; k<=5; k++) {
        (function(k) { // We are creating a closure using IIFE (Immediately Invoked Function Expression).
            setTimeout(function() {
                console.log("3-"+k); // This will print 1 to 5 as we are passing k as argument to IIFE and it creates a new function scope for k.
            }, k*1000);
        })(k);
    }
}
print3();

//------------------


// 1. setTimeout stores the function in a different place and attached a timer to it, when the timer is finished it rejoins the call stack and executed.
// 2. Without closure the var reference gives the latest value as it does not retain the original value but rather has the reference so any update in value after timeout will be shown.
// 3. If we use let/const because they have block scope, every time a new copy of variable is attached, thus this can be done without closure.