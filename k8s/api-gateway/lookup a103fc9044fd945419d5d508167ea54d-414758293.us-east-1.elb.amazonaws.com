{
    "LoadBalancerDescriptions": [
        {
            "LoadBalancerName": "a103fc9044fd945419d5d508167ea54d",
            "DNSName": "a103fc9044fd945419d5d508167ea54d-414758293.us-east-1.elb.amazonaws.com",
            "CanonicalHostedZoneName": "a103fc9044fd945419d5d508167ea54d-414758293.us-east-1.elb.amazonaws.com",
            "CanonicalHostedZoneNameID": "Z35SXDOTRQ7X7K",
            "ListenerDescriptions": [
                {
                    "Listener": {
                        "Protocol": "TCP",
                        "LoadBalancerPort": 8080,
                        "InstanceProtocol": "TCP",
                        "InstancePort": 30760
                    },
                    "PolicyNames": []
                }
            ],
            "Policies": {
                "AppCookieStickinessPolicies": [],
                "LBCookieStickinessPolicies": [],
                "OtherPolicies": []
            },
            "BackendServerDescriptions": [],
            "AvailabilityZones": [
                "us-east-1c",
                "us-east-1d"
            ],
            "Subnets": [
                "subnet-0390a64bf99264bee",
                "subnet-077d417019985bc26"
            ],
            "VPCId": "vpc-0f21ad17c2149ee6a",
            "Instances": [
                {
                    "InstanceId": "i-0b5beec7c6d4806fa"
                },
                {
                    "InstanceId": "i-0cf4a653858fe4fa0"
                }
            ],
            "HealthCheck": {
                "Target": "TCP:30760",
                "Interval": 10,
                "Timeout": 5,
                "UnhealthyThreshold": 6,
                "HealthyThreshold": 2
            },
            "SourceSecurityGroup": {
                "OwnerAlias": "526344316896",
                "GroupName": "k8s-elb-a103fc9044fd945419d5d508167ea54d"
            },
            "SecurityGroups": [
                "sg-0dfa88a58c54f36a7"
            ],
            "CreatedTime": "2026-07-05T17:58:33.700000+00:00",
            "Scheme": "internet-facing"
        }
    ]
}
