System.config({
    transpiler: 'typescript',

    typescriptOptions: {
      emitDecoratorMetadata: true
    },

    map: {
      app: "/resources/app"
    },

      packages: {
        app: {
          main: './boot.ts',
          defaultExtension: 'ts'
        }
      }
  });