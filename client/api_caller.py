import requests


class ApiCaller:
    @staticmethod
    def send_request(endpoint_url, headers, request_payload):
        try:
            api_response = requests.post(
                endpoint_url,
                request_payload,
                headers=headers
            )
            api_response.raise_for_status()
            return api_response
        except requests.exceptions.RequestException as exception:
            print(exception)
