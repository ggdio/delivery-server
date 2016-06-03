"use strict";

angular.module('app.component.version', [
  'app.component.version.filter',
  'app.component.version.directive'
])

.value('version', '0.0.1');