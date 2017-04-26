module.exports = {
    entry: {
        app: "./app/boot.ts",
        polyfill: "./app/webpack/polyfill.ts",
        vendor: "./app/webpack/vendor.ts"
    },
    output: {
        filename: "./out/[name].js"
    },
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loader: "ts-loader",
                exclude: /node_modules/
            }
        ]
    },
    resolve: {
        extensions: [".js", ".ts"]
    }
}