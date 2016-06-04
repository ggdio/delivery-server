var gulp = require("gulp");
var wiredep = require("wiredep");
var inject = require("gulp-inject");
var connect = require("gulp-connect");
var browserify = require('browserify');
var source = require('vinyl-source-stream');

gulp.task("inject-vendor", function() {
	wiredep({src: "./app/index.html"});
});

gulp.task("inject-own", function() {
	gulp.src("./app/index.html")
		.pipe(inject(gulp.src(["./app/component/*.js", ".app/main/*.js", "./app/*.js", "./assets/**/*.js", "./assets/**/*.css"], {read: false})))
		.pipe(gulp.dest("./app"));
});

gulp.task("browserify", function() {
    return browserify("./app/app.module.js").bundle()
		.pipe(source("main.js"))
        .pipe(gulp.dest("./dist/js/"));
});

gulp.task("connect", function () {
  connect.server({
    root: "dist",
    port: 4000,
    livereload: true
  });
});

gulp.task("default", ["inject-vendor", "inject-own"]);