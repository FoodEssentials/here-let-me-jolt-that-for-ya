import json

from pyjson5 import decode_io, encode

from client.api_caller import ApiCaller

caller = ApiCaller()

if __name__ == '__main__':
    with open("input_output_files/input.json", "r") as product_file:
        product = product_file.read()

    with open("input_output_files/jolt.json5", "r") as spec_file:
        # ! note the decode/encode allows us to tear out anything that is json5 compatible but not json compatible
        spec = encode(decode_io(spec_file))

    response = caller.send_request(
        "http://localhost:8080/transform",
        {'Content-Type': 'application/json'},
        encode({"spec": spec, "input": product})
    )

    with open("input_output_files/output.json5", "w") as output_file:
        json.dump(response.json(), output_file, indent=4)
