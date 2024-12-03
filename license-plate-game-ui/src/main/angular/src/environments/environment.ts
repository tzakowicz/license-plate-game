// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  route: "localhost:8080",
  build_date: new Date(),
  countries: [{
    "code": "US",
    "territories": [
      {
        "code":"AA",
        "found": false
      },{
        "code":"AB",
        "found": false
      },{
        "code":"AC",
        "found": false
      },{
        "code":"BA",
        "found": false
      },{
        "code":"BB",
        "found": false
      },{
        "code":"BC",
        "found": false
      }
    ]
  }]
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
