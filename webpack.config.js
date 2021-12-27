const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const NodePolyfillPlugin = require("node-polyfill-webpack-plugin")

module.exports = {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        host: '0.0.0.0',
        port: 3000,
        hot: true,
        open: true,
        static: path.resolve(__dirname, 'dist'),
        // if bundling front end, add server proxy config here
        proxy: {
        },
    },
    entry: {
        'bundle': './src/index.js',
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "[name].js",
        assetModuleFilename: '[name][ext]',
        clean: true,
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            inject: true,
            title: "Webpack Production",
            template: path.resolve(__dirname, 'src', 'index.html')
        }),
        new NodePolyfillPlugin(),
        new MiniCssExtractPlugin()
    ],
    module: {
        rules: [
            // all js/ts files, exclude node modules
            {
                test: /\.(js|ts)x?$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react'],
                        plugins: [
                            "babel-plugin-styled-components"
                        ]
                    },
                },
            },
            // css files
            {
                test: /\.css$/,
                use: [MiniCssExtractPlugin.loader, "css-loader"],
            },
            // font files
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                {
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]',
                        outputPath: 'fonts/'
                    }
                }
                ]
            },
            /**
             * image files => new in Webpack 5
             * instead of using file-loader, set type to asset/resources
             * name property effects name in build folder
             */
            {
                test: /\.(pdf|gif|png|jpe?g|svg)$/,
                type: 'asset/resources',
            }
        ],
    },
    resolve: {
        aliasFields: ['browser', 'browser.esm'],
        extensions: ['.tsx', '.ts', '.js', '.jsx'],
    },
}