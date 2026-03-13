#!/usr/bin/env bash
# Parses JUnit XML test results into a sorted text file for branch comparison.
# Usage: ./scripts/parse-test-results.sh [output-file]
#   output-file defaults to test-baseline-<branch>.txt

set -euo pipefail

RESULTS_DIR="build/test-results/test"
BRANCH=$(git rev-parse --abbrev-ref HEAD 2>/dev/null || echo "unknown")
OUTPUT="${1:-test-baseline-${BRANCH}.txt}"

if [ ! -d "$RESULTS_DIR" ]; then
    echo "ERROR: No test results found in $RESULTS_DIR"
    echo "Run './gradlew test --continue' first."
    exit 1
fi

# Parse all JUnit XML files using python (available on macOS)
python3 - "$RESULTS_DIR" "$OUTPUT" <<'PYEOF'
import sys, os, xml.etree.ElementTree as ET

results_dir = sys.argv[1]
output_file = sys.argv[2]

tests = []
total = passed = failed = errored = skipped = 0

for fname in sorted(os.listdir(results_dir)):
    if not fname.endswith('.xml'):
        continue
    path = os.path.join(results_dir, fname)
    try:
        tree = ET.parse(path)
    except ET.ParseError:
        continue
    root = tree.getroot()

    # Handle both <testsuite> root and <testsuites> wrapper
    suites = root.findall('.//testsuite') if root.tag == 'testsuites' else [root]

    for suite in suites:
        classname = suite.get('name', '')
        for tc in suite.findall('testcase'):
            total += 1
            name = tc.get('name', '')
            cls = tc.get('classname', classname)
            fqn = f"{cls}.{name}"

            if tc.find('failure') is not None:
                status = 'FAIL'
                failed += 1
            elif tc.find('error') is not None:
                status = 'ERROR'
                errored += 1
            elif tc.find('skipped') is not None:
                status = 'SKIP'
                skipped += 1
            else:
                status = 'PASS'
                passed += 1

            tests.append((status, fqn))

tests.sort(key=lambda x: x[1])

with open(output_file, 'w') as f:
    f.write(f"# Test Baseline - branch: {os.popen('git rev-parse --abbrev-ref HEAD 2>/dev/null').read().strip()}\n")
    f.write(f"# Commit: {os.popen('git rev-parse --short HEAD 2>/dev/null').read().strip()}\n")
    f.write(f"# Date: {os.popen('date -u +%Y-%m-%dT%H:%M:%SZ').read().strip()}\n")
    f.write(f"# Total: {total} | Pass: {passed} | Fail: {failed} | Error: {errored} | Skip: {skipped}\n")
    f.write("#\n")
    for status, fqn in tests:
        f.write(f"{status:<6} | {fqn}\n")

print(f"Baseline written to: {output_file}")
print(f"  Total: {total} | Pass: {passed} | Fail: {failed} | Error: {errored} | Skip: {skipped}")
PYEOF