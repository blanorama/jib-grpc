local k = import 'github.com/grafana/jsonnet-libs/ksonnet-util/kausal.libsonnet';
local deployment = k.apps.v1.deployment;
local container = k.core.v1.container;
local port = k.core.v1.containerPort;
local ingress = k.networking.v1.ingress;
local ingressTLS = k.networking.v1.ingressTLS;
local ingressRule = k.networking.v1.ingressRule;
local httpIngressPath = k.networking.v1.httpIngressPath;
{
  _config:: {
    local config = self,
    domain: 'otlp-quarkus.mayflower.cloud',
    tag: '0.1.4',
    service: {
      name: 'otlp-quarkus',
      domain: config.domain,
      image: 'ghcr.io/lecodeski/quarkus-native-mandrel-jib-otlp:%s' % config.tag,
    },
  },
  microservice: {
    local cfg = $._config.service,
    local portREST = 'rest',
    local portGRPC = 'grpc',
    deployment: deployment.new(
      name=cfg.name,
      replicas=1,
      containers=[
        container.new(cfg.name, cfg.image)
        + container.withPorts([port.new(portREST, 8080), port.new(portGRPC, 9000)])
      ],
    ),
    service: k.util.serviceFor(self.deployment, nameFormat="%(port)s"),
    ingress: ingress.new(cfg.name)
    + ingress.metadata.withAnnotations({
      'kubernetes.io/ingress.class': 'nginx',
      'cert-manager.io/cluster-issuer': 'letsencrypt-prod',
    })
    + ingress.spec.withTls(
      ingressTLS.withHosts(cfg.domain)
      + ingressTLS.withSecretName('%s-tls' % cfg.name)
    )
    + ingress.spec.withRules(
      ingressRule.withHost(cfg.domain)
      + ingressRule.http.withPaths(
        httpIngressPath.withPath('/')
        + httpIngressPath.withPathType('Prefix')
        + httpIngressPath.backend.service.withName(cfg.name)
        + httpIngressPath.backend.service.port.withName(portREST)
        + httpIngressPath.backend.service.port.withName(portGRPC)
      )
    ),
  },
}
