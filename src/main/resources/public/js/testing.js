function isEven(val) {
    return val % 2 === 0;
}
 
test('isEven()', function() {
    ok(isEven(0), 'Zero is an even number');
    ok(isEven(2), 'So is two');
    ok(isEven(-4), 'So is negative four');
    ok(!isEven(1), 'One is not an even number');
    ok(!isEven(-7), 'Neither is negative seven');
})
// Let's test this function
function isEven(val) {
    return val % 2 === 0;
}
 
test('isEven()', function() {
    ok(isEven(0), 'Zero is an even number');
    ok(isEven(2), 'So is two');
    ok(isEven(-4), 'So is negative four');
    ok(!isEven(1), 'One is not an even number');
    ok(!isEven(-7), 'Neither does negative seven');
 
    // Fails
    ok(!isEven(3), 'Three is an even number');
})

QUnit.module("Test a parent component", {
    beforeEach: function () {
        this.sandbox = Sinon.sandbox.create();

        //Set up the spec helper.  Since React.createElement is static use sandbox.
        //Pass in the components you want to mock
        new ReactSpecHelper(this.sandbox).stubChildren([ChildA, ChildB]);
    },
    afterEach: function () {
        this.sandbox.restore();
    }
});
QUnit.test("It renders child A with the correct data", function(){
    var data = {a: "a", b: "b"};
    var view = TestUtils.renderIntoDocument(<Parent data={data} />);

    var childA = TestUtils.scryRenderedDOMComponentsWithClass(view,"ChildA");
    equal(childA.length, 1);
    equal(childA[0].props.data, "a");
});

