# njulawclient

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### API DOC


1. searchDoc
```
params : {kw}  // keyword  

result: [
        { 
        id: "",
        title: "",
        summary: "" 
        }
        ]
```

2. getDocContent
```
params : {id}  // doc id

result: 
        { 
        id: "",
        title: "",
        summary: "",
        content
        }
```

