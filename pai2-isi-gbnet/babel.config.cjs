module.exports = {
  presets: [
    "@babel/preset-env",
    "@babel/preset-react"
  ],
  plugins: [
    ["@babel/plugin-transform-runtime", { "regenerator": true }]
  ],
  env: {
    "test": {
      "plugins": ["@babel/plugin-transform-modules-commonjs"]
    }
  },
  sourceType: "unambiguous"
};
