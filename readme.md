# Here, let me jolt that for you

## :exhausted: why this tool exists

Troubleshooting jolt documents is a pain in the ass. It's a functional pipeline where the inputs and outputs of steps
are invisible to you, so when we build massive jolt docs with lots of steps and temporary variables in the middle it
makes it wildly frustrating to troubleshoot.

So, after trying several different methods of troubleshooting jolts that we don't have unit tests for I landed on this
as a tool for making the best of a bad situation.

## What it is

This codebase is a toolset for testing out and troubleshooting a jolt document fired against a given input. The output
of the transformation is written to a json file, pretty printed, so you can review the output. Really, it's
like a local version of [the online jolt tester tool](https://jolt-demo.appspot.com/#inception), but it has a couple of
key advantages:

- There's no request size limitation
    - The online tool limits the size of the requests and our products and jolt's are so big they don't work with the
      online tool without modification
- We're in an IDE, so we can take advantage of all of the features an IDE gives (syntax highlighting, structure
  navigation, keyboard shortcuts, etc)
- The server is local and launched via intellij, so you can run it with the debugger
    - Sometimes jolts fail on the server side, so it's nice to be able to pause and see what's going on while the
      transformation is being run
- The jolt input file is a json5 file, not regular json
    - This is key, because json5 allows you to add comments, so you can comment out steps to see what the output of the
      transformation is part of the way through the file.
    - The comments also allow you to add notes to the jolt as you troubleshoot it
- It's independent of our product-model-transformation tooling
    - Here you can concentrate on just the jolt transformation vs all of the other layers TAP adds

## How to use it

The basic steps are:

- Clone the codebase
- Run the gradle refresh to install the dependencies
- Install the python dependencies
    - This can be done via the command palette or python tool window
- Start the java app
    - The project settings are saved to the codebase, so you should be able to just launch it
- update your input_output_files
    - input.json should contain the json you want to transform (likely the indexed product json)
    - jolt.json5 should be your jolt document containing all of the transformation steps
- open the output.json file, this is where the output of the transformation will be dumped
- Run the python script in `./client/main.py`

![demo](readme_attachments/using-tool.gif)

I tend to open the input output files in different splits so I can modify the jolt, run the script, and see the output
immediately.

Once you're done troubleshooting the jolt you can copy the jolt out of the jolt.json5 file and put it wherever it's
supposed to go

**IMPORTANT: remember to strip out any json5 stuff like comments before you put the updated jolt wherever it needs to go
**. 



